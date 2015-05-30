package com.bourneless.engine.main;

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
	
	// Entities
	
	public BufferedImage player;
	
	// Music
	
	public Clip menuMusic;
	
	public ResourceLoader() {
		splashImage = getBufferedImage("res/engine/splash.png");
		icon = getBufferedImage("res/client/icon.png");
		
		// Menu
		
		menuBackground = getBufferedImage("res/menu/menuImage.png");
		
		// Entities
		
		player = getBufferedImage("res/entity/player/player.png");
		
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
