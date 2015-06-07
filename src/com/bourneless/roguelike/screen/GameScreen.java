package com.bourneless.roguelike.screen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.screen.MenuScreen;
import com.bourneless.engine.screen.Screen;
import com.bourneless.roguelike.game.Instance;

public class GameScreen extends Screen {

	private Instance instance;
	private boolean isReady = false;

	private boolean paused = false;

	public GameScreen() {
		instance = new Instance();
		isReady = true;
	}

	public void update() {
		if (!paused) {
			instance.update();
		}
	}

	public void paint(Graphics2D g) {
		instance.paint(g);

		if (paused) {
			g.setColor(Color.BLACK);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.8f));
			g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1f));
			g.drawImage(Main.resourceLoader.paused, 0, Main.GAME_HEIGHT / 2
					- Main.resourceLoader.paused.getHeight() / 2, null);

		}
	}

	public void keyPressed(KeyEvent e) {
		if (!paused) {
			instance.keyPressed(e);
		}
		if (e.getKeyCode() == 27) {
			paused = !paused;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (!paused) {
			instance.keyReleased(e);
		}
	}

	public boolean getIsReady() {
		return this.isReady;
	}
}
