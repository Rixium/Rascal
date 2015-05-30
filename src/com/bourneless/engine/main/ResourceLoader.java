package com.bourneless.engine.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class ResourceLoader {

	public BufferedImage splashImage;
	public BufferedImage icon;

	// Create Fields here

	// Menu

	public BufferedImage menuBackground;

	// Menu Buttons

	public BufferedImage startGameButtonImage;
	public BufferedImage exitGameButtonImage;

	// Entities

	// Player

	public BufferedImage[] player;

	// Tiles

	public BufferedImage[] tiles;
	public BufferedImage[] wallTiles;

	// Map

	public BufferedImage[] rooms;

	// Music

	public Clip menuMusic;

	public ResourceLoader() {
		splashImage = getBufferedImage("res/engine/splash.png");
		icon = getBufferedImage("res/client/icon.png");

		// Menu

		menuBackground = getBufferedImage("res/menu/menuImage.png");

		// Menu Buttons

		startGameButtonImage = getBufferedImage("res/menu/buttons/newGameButton.png");
		exitGameButtonImage = getBufferedImage("res/menu/buttons/exitGameButton.png");

		// Entities

		// Tiles

		BufferedImage wallTileSheet = getBufferedImage("res/tile/wallTiles.png");
		int rows = 10;
		int cols = 10;
		int iteration = 0;
		wallTiles = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				wallTiles[iteration] = wallTileSheet
						.getSubimage(j * 64, i * 192, 64, 192);
				iteration++;
			}
		}
		
		BufferedImage tileSheet = getBufferedImage("res/tile/tileSheet.png");
		rows = tileSheet.getWidth() / 64;
		cols = tileSheet.getHeight() / 64;
		iteration = 0;
		tiles = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				tiles[iteration] = tileSheet
						.getSubimage(j * 64, i * 64, 64, 64);
				iteration++;
			}
		}

		BufferedImage playerSheet = getBufferedImage("res/entity/player/playerSheet.png");
		rows = 10;
		cols = 10;
		iteration = 0;
		player = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				player[iteration] = playerSheet.getSubimage(j * 64, i * 128,
						64, 128);
				iteration++;
			}
		}

		// Map

		rooms = new BufferedImage[3];
		rooms[0] = getBufferedImage("res/rooms/room1.png");
		rooms[1] = getBufferedImage("res/rooms/room2.png");
		rooms[2] = getBufferedImage("res/rooms/room3.png");

		// Music

		menuMusic = loadClip("/music/menuMusic.wav");
	}

	public Clip loadClip(String filename) {
		Clip clip = null;

		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(getClass().getResource(filename));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clip;
	}

	@SuppressWarnings("static-access")
	public void playClip(Clip clip, float volume, boolean loop) {

		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(0);
		FloatControl gainControl = (FloatControl) clip
				.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
		if (loop) {
			clip.loop(clip.LOOP_CONTINUOUSLY);
		}
		clip.start();
	}

	public void stopClip(Clip clip) {
		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(0);
	}

	public static Image getImage(String url) {

		try {
			return ImageIO.read(new File(url));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static BufferedImage getBufferedImage(String url) {

		try {
			return ImageIO.read(new File(url));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}
