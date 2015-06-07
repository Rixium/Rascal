package com.bourneless.engine.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.Timer;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.engine.util.Button;
import com.bourneless.roguelike.screen.LoadScreen;

public class MenuScreen extends Screen {

	private Button[] menuButtons = new Button[2];

	private String vNumber = "v 0.3.44";
	private Font font = new Font("Arial", Font.PLAIN, 10);
	private Font messageFont = new Font("A Font With Serifs",
			Font.TRUETYPE_FONT, 40);

	private Random random = new Random();

	private String randomMessage = "Wow!";

	private Color color = new Color(1841431);

	public MenuScreen() {
		randomMessage = Main.resourceLoader.randomMessages.get(random
				.nextInt(Main.resourceLoader.randomMessages.size()));
		if (!Main.resourceLoader.menuMusic.isRunning()) {
			Main.resourceLoader.playClip(Main.resourceLoader.menuMusic, -20.0f,
					true);
		}

		initialize();
	}

	public void initialize() {
		menuButtons[0] = new Button(Main.resourceLoader.startGameButtonImage,
				new Vector2(0, Main.GAME_HEIGHT / 2
						+ Main.resourceLoader.startGameButtonImage.getHeight()));
		menuButtons[1] = new Button(Main.resourceLoader.exitGameButtonImage,
				new Vector2(0, Main.GAME_HEIGHT / 2
						+ Main.resourceLoader.startGameButtonImage.getHeight()
						* 2 + 20));
	}

	public void paint(Graphics2D g) {
		g.drawImage(Main.resourceLoader.menuBackground, 0, 0, Main.GAME_WIDTH,
				Main.GAME_HEIGHT, null);

		for (Button button : menuButtons) {
			button.paint(g);
		}

		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(vNumber, 10, Main.GAME_HEIGHT - 10);

		drawString(g, Main.GAME_WIDTH / 2 + Main.GAME_WIDTH / 12,
				Main.GAME_HEIGHT / 4 - 50, randomMessage);

	}

	public void drawString(Graphics2D g2d, double x, double y, String text) {
		g2d.setFont(messageFont);
		int stringLength = (int) g2d.getFontMetrics()
				.getStringBounds(text, g2d).getWidth();
		int stringHeight = (int) g2d.getFontMetrics()
				.getStringBounds(text, g2d).getHeight();
		int start = Main.GAME_WIDTH / 2 - stringLength / 2;
		int xPos = Main.GAME_HEIGHT - stringHeight + 10;

		g2d.setColor(color);
		g2d.drawString(text, start, xPos);

	}

	public void update() {

	}

	public void mouseMoved(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY() - 20, 10, 10);
		if (mouseRect.intersects(menuButtons[0].getRect())) {
			menuButtons[0].mouseOver();
		} else if (mouseRect.intersects(menuButtons[1].getRect())) {
			menuButtons[1].mouseOver();
		} else {
			menuButtons[1].mouseOff();
			menuButtons[0].mouseOff();
		}
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY() - 20, 10, 10);

		if (mouseRect.intersects(menuButtons[0].getRect())) {
			Main.game.setScreen(new LoadScreen());
		} else if (mouseRect.intersects(menuButtons[1].getRect())) {
			System.exit(0);
		}
	}

	public void loadGame() {

	}

}
