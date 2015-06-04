package com.bourneless.engine.screen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.engine.util.Button;
import com.bourneless.roguelike.screen.LoadScreen;

public class MenuScreen extends Screen {

	private Button[] menuButtons = new Button[2];

	public MenuScreen() {
		Main.resourceLoader.playClip(Main.resourceLoader.menuMusic, -10.0f,
				true);

		initialize();
	}

	public void initialize() {
		menuButtons[0] = new Button(Main.resourceLoader.startGameButtonImage,
				new Vector2(10, 10));
		menuButtons[1] = new Button(Main.resourceLoader.exitGameButtonImage,
				new Vector2(10,
						10 + Main.resourceLoader.exitGameButtonImage
								.getHeight() + 10));
	}

	public void paint(Graphics2D g) {
		g.drawImage(Main.resourceLoader.menuBackground, 0, 0, Main.GAME_WIDTH,
				Main.GAME_HEIGHT, null);

		for (Button button : menuButtons) {
			button.paint(g);
		}
	}

	public void update() {

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
