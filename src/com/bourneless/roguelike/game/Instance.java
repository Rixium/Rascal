package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.map.Map;

public class Instance {
	
	Map map = new Map();

	public Instance() {

	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Game Screen", 10, 10);
		
		map.paint(g);
	}

	public void keyPressed(KeyEvent e) {

	}

}
