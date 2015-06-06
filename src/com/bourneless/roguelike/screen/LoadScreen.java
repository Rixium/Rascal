package com.bourneless.roguelike.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.screen.Screen;

public class LoadScreen extends Screen {

	GameScreen screen;
	boolean hasPainted = false;
	boolean creatingInstance = false;

	private Random random = new Random();
	private Font font;
	private String loadingText = "";

	public LoadScreen() {
		font = new Font("A Font With Serifs", Font.PLAIN, 50);
		System.out.println("Creating World..");
	}

	public void update() {
		if (hasPainted && !creatingInstance) {
			screen = new GameScreen();
			creatingInstance = true;
		}
		if (screen != null) {
			if (screen.getIsReady()) {
				Main.game.setScreen(screen);
			}
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		int stringLength = (int) g.getFontMetrics()
				.getStringBounds(loadingText, g).getWidth();
		int stringHeight = (int) g.getFontMetrics()
				.getStringBounds(loadingText, g).getHeight();
		int start = Main.GAME_WIDTH / 2 - stringLength / 2;
		int xPos = Main.GAME_HEIGHT / 2 - stringHeight / 2;
		g.drawString(loadingText, start, xPos);
		hasPainted = true;
	}

	public void changeString() {
		loadingText = Main.resourceLoader.loadingText.get(random
				.nextInt(Main.resourceLoader.loadingText.size()));
		paint((Graphics2D) Main.game.getGraphics());
	}
}
