package com.bourneless.engine.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

	public BufferedImage[] monsterImages = new BufferedImage[100];

	// Player

	public BufferedImage[] player;

	// Tiles

	public BufferedImage[] tiles;
	public BufferedImage[] wallTiles;
	public BufferedImage fog;

	// Door

	public BufferedImage[] door;
	public BufferedImage[] sideDoor = new BufferedImage[2];

	// Chest
	public BufferedImage[] chest = new BufferedImage[2];

	// Animations

	public BufferedImage[] moveLeft = new BufferedImage[3];
	public BufferedImage[] moveRight = new BufferedImage[3];
	public BufferedImage[] moveUp = new BufferedImage[3];
	public BufferedImage[] moveDown = new BufferedImage[3];

	// UI

	public BufferedImage healthBar;

	public BufferedImage statScreen;
	public BufferedImage inventoryScreen;	

	public BufferedImage paused;

	public BufferedImage deathScreen;

	// Portraits

	public BufferedImage[] playerPortraits = new BufferedImage[1];
	
	// item
	public BufferedImage[] itemInvImages = new BufferedImage[100];

	// Music

	public Clip menuMusic;

	// Sounds

	public Clip hitSound;
	public Clip breakSound;

	public Clip walkSounds[] = new Clip[4];

	public Clip monsterDeath[] = new Clip[3];

	public Clip playerHurt[] = new Clip[3];
	public Clip playerDeath[] = new Clip[3];

	public Clip hitSounds[] = new Clip[4];
	public Clip buttonHover;

	public Clip closeBook;
	public Clip openBook;

	// Fonts

	public Font gameFont;

	// Alerts

	public BufferedImage exclamation;

	// Text

	public ArrayList<String> loadingText = new ArrayList<String>();
	public ArrayList<String> names = new ArrayList<String>();
	public ArrayList<String> specialities = new ArrayList<String>();
	public ArrayList<String> titles = new ArrayList<String>();
	public ArrayList<String> randomMessages = new ArrayList<String>();

	private ClassLoader cl = this.getClass().getClassLoader();

	// Date Files

	public JSONParser parser = new JSONParser();
	public JSONArray monsters;

	public ResourceLoader() {
		splashImage = getBufferedImage("/engine/splash.png");
		icon = getBufferedImage("/client/icon.png");

		// Menu

		menuBackground = getBufferedImage("/menu/menuImage.png");

		// Menu Buttons

		startGameButtonImage = getBufferedImage("/menu/buttons/newGameButton.png");
		exitGameButtonImage = getBufferedImage("/menu/buttons/exitGameButton.png");

		// Entities

		// Monsters

		// Tiles

		fog = getBufferedImage("/mechanics/fog.png");

		BufferedImage wallTileSheet = getBufferedImage("/tile/wallTiles.png");
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

		BufferedImage tileSheet = getBufferedImage("/tile/tileSheet.png");
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
		BufferedImage playerSheet = getBufferedImage("/entity/player/playerSheet.png");
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

		BufferedImage doorSheet = getBufferedImage("/entity/door/doorSheet.png");
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

		sideDoor[0] = getBufferedImage("/entity/door/sideDoor1.png");
		sideDoor[1] = getBufferedImage("/entity/door/sideDoor2.png");

		BufferedImage monsterSheet = getBufferedImage("/entity/mob/monsters.png");

		rows = 10;
		cols = 10;
		iteration = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				monsterImages[iteration] = monsterSheet.getSubimage(j * 64,
						i * 64, 64, 64);
				iteration++;
			}
		}

		// Chest

		BufferedImage chestSheet = getBufferedImage("/entity/chest/chest.png");
		rows = 1;
		cols = 2;
		iteration = 0;
		chest = new BufferedImage[rows * cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				chest[iteration] = chestSheet.getSubimage(j * 64, i * 64, 64,
						64);
				iteration++;
			}
		}
		
		// Items
		
		BufferedImage itemSheet = getBufferedImage("/item/itemSheet.png");
		rows = 10;
		cols = 10;
		iteration = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				itemInvImages[iteration] = itemSheet.getSubimage(j * 32, i * 32, 32,
						32);
				iteration++;
			}
		}

		// Music

		menuMusic = loadClip("/music/menuMusic.wav");

		// Sounds

		hitSound = loadClip("/audio/breakableentity/hit.wav");
		breakSound = loadClip("/audio/breakableentity/break.wav");

		walkSounds[0] = loadClip("/audio/walk/w1.wav");
		walkSounds[1] = loadClip("/audio/walk/w2.wav");
		walkSounds[2] = loadClip("/audio/walk/w3.wav");
		walkSounds[3] = loadClip("/audio/walk/w4.wav");

		monsterDeath[0] = loadClip("/audio/mob/death/1.wav");
		monsterDeath[1] = loadClip("/audio/mob/death/2.wav");
		monsterDeath[2] = loadClip("/audio/mob/death/3.wav");

		hitSounds[0] = loadClip("/audio/hit/1.wav");
		hitSounds[1] = loadClip("/audio/hit/2.wav");
		hitSounds[2] = loadClip("/audio/hit/3.wav");
		hitSounds[3] = loadClip("/audio/hit/4.wav");

		playerHurt[0] = loadClip("/audio/playerhurt/1.wav");
		playerHurt[1] = loadClip("/audio/playerhurt/2.wav");
		playerHurt[2] = loadClip("/audio/playerhurt/3.wav");

		playerDeath[0] = loadClip("/audio/playerdeath/1.wav");
		playerDeath[1] = loadClip("/audio/playerdeath/2.wav");
		playerDeath[2] = loadClip("/audio/playerdeath/3.wav");

		openBook = loadClip("/audio/ui/openBook.wav");
		closeBook = loadClip("/audio/ui/closeBook.wav");

		buttonHover = loadClip("/audio/menu/buttonHover.wav");

		// UI

		healthBar = getBufferedImage("/ui/healthBar.png");
		statScreen = getBufferedImage("/ui/menus/characterStats.png");
		paused = getBufferedImage("/ui/menus/pausedOverlay.png");
		deathScreen = getBufferedImage("/ui/deathScreen.png");
		inventoryScreen = getBufferedImage("/ui/menus/inventoryScreen.png");

		// Portraits

		playerPortraits[0] = getBufferedImage("/ui/portraits/1.png");

		// Fonts

		InputStream is = ResourceLoader.class
				.getResourceAsStream("/fonts/font.ttf");

		try {
			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}

		gameFont = new Font("Calibri", Font.TRUETYPE_FONT, 10);

		// Alerts

		exclamation = getBufferedImage("/alerts/exclamation.png");

		// Text

		Scanner s = null;

		s = new Scanner(
				ResourceLoader.class
						.getResourceAsStream("/text/wittyLines.txt"));

		while (s.hasNextLine()) {
			loadingText.add(s.nextLine());
		}
		s.close();

		// Names

		s = new Scanner(
				ResourceLoader.class.getResourceAsStream("/text/names.txt"));

		while (s.hasNextLine()) {
			names.add(s.nextLine());
		}
		s.close();

		// Specialities

		s = new Scanner(
				ResourceLoader.class
						.getResourceAsStream("/text/specialities.txt"));

		while (s.hasNextLine()) {
			specialities.add(s.nextLine());
		}
		s.close();

		// Titles

		s = new Scanner(
				ResourceLoader.class.getResourceAsStream("/text/titles.txt"));

		while (s.hasNextLine()) {
			titles.add(s.nextLine());
		}
		s.close();

		// Random Messages

		s = new Scanner(
				ResourceLoader.class
						.getResourceAsStream("/text/randomMessages.txt"));

		while (s.hasNextLine()) {
			randomMessages.add(s.nextLine());

		}
		s.close();

		// JSON

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				ResourceLoader.class.getResourceAsStream("/data/monsters.txt")));

		try {
			monsters = (JSONArray) parser.parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public Clip loadClip(String filename) {
		Clip clip = null;

		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(ResourceLoader.class
							.getResource(filename));
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
			return ImageIO.read(Main.resourceLoader.getClass().getClassLoader()
					.getResourceAsStream(url));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static BufferedImage getBufferedImage(String url) {
		try {
			return ImageIO.read(ResourceLoader.class.getResourceAsStream(url));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
}
