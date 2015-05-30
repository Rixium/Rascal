package com.bourneless.roguelike.screen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.screen.Screen;
import com.bourneless.roguelike.game.Instance;

public class GameScreen extends Screen {

	private Instance instance;

	public GameScreen() {
		instance = new Instance();
	}

	public void update() {
		instance.update();
	}

	public void paint(Graphics2D g) {
		instance.paint(g);
	}

	public void keyPressed(KeyEvent e) {
		instance.keyPressed(e);
	}
}
