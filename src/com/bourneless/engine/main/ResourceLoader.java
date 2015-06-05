package com.bourneless.engine.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	public BufferedImage fog;

	// Door

	public BufferedImage[] door;
	public BufferedImage[] sideDoor = new BufferedImage[2];

	// Map

	public BufferedImage[] rooms;

	// Animations

	public BufferedImage[] moveLeft = new BufferedImage[3];
	public BufferedImage[] moveRight = new BufferedImage[3];
	public BufferedImage[] moveUp = new BufferedImage[3];
	public BufferedImage[] moveDown = new BufferedImage[3];

	// Music

	public Clip menuMusic;

	// Sounds

	public Clip hitSound;
	public Clip breakSound;

	public Clip walkSounds[] = new Clip[4];

	// Fonts

	public Font gameFont;

	// Text

	public ArrayList<String> loadingText = new ArrayList<String>();

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

		fog = getBufferedImage("res/mechanics/fog.png");

		BufferedImage wallTileSheet = getBufferedImage("res/tile/wallTiles.png");
		int rows = 10;
		int cols = 10;
		int iteration = 0;
		wallTiles = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				wallTiles[iteration] = wallTileSheet.getSubimage(j * 64,
						i * 64, 64, 64);
				iteration++;
			}
		}

		BufferedImage tileSheet = getBufferedImage("res/tile/tileSheet.png");
		rows = 1;
		cols = 5;
		iteration = 0;
		tiles = new BufferedImage[5];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				tiles[iteration] = tileSheet
						.getSubimage(j * 64, i * 64, 64, 64);
				iteration++;
			}
		}

		// Player Animations
		BufferedImage playerSheet = getBufferedImage("res/entity/player/playerSheet.png");
		rows = 4;
		cols = 3;
		iteration = 0;
		player = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				player[iteration] = playerSheet.getSubimage(j * 64, i * 128,
						64, 128);
				iteration++;
			}
		}

		moveDown[0] = player[0];
		moveDown[1] = player[1];
		moveDown[2] = player[2];

		moveLeft[0] = player[3];
		moveLeft[1] = player[4];
		moveLeft[2] = player[5];

		moveRight[0] = player[6];
		moveRight[1] = player[7];
		moveRight[2] = player[8];

		moveUp[0] = player[9];
		moveUp[1] = player[10];
		moveUp[2] = player[11];

		// Door

		BufferedImage doorSheet = getBufferedImage("res/entity/door/doorSheet.png");
		rows = 1;
		cols = 2;
		iteration = 0;
		door = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				door[iteration] = doorSheet.getSubimage(j * 64, i * 128, 64,
						128);
				iteration++;
			}
		}

		sideDoor[0] = getBufferedImage("res/entity/door/sideDoor1.png");
		sideDoor[1] = getBufferedImage("res/entity/door/sideDoor2.png");

		// Map

		rooms = new BufferedImage[2];
		rooms[0] = getBufferedImage("res/rooms/room1.png");
		rooms[1] = getBufferedImage("res/rooms/room2.png");

		// Music

		menuMusic = loadClip("/music/menuMusic.wav");

		// Sounds

		hitSound = loadClip("/audio/breakableentity/hit.wav");
		breakSound = loadClip("/audio/breakableentity/break.wav");

		walkSounds[0] = loadClip("/audio/walk/w1.wav");
		walkSounds[1] = loadClip("/audio/walk/w2.wav");
		walkSounds[2] = loadClip("/audio/walk/w3.wav");
		walkSounds[3] = loadClip("/audio/walk/w4.wav");

		// Fonts

		try {
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
					"res/fonts/font.ttf")));
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}

		// Text

		Scanner s = null;

		try {
			s = new Scanner(new File("res/text/wittyLines.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (s.hasNextLine()) {
			loadingText.add(s.nextLine());
		}
		s.close();

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
