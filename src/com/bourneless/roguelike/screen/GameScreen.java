package com.bourneless.roguelike.screen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.screen.MenuScreen;
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
		
		if(e.getKeyCode() == 27) {
			Main.game.setScreen(new MenuScreen());
		}
	}
	
	public void keyReleased(KeyEvent e) {
		instance.keyReleased(e);
	}
}
