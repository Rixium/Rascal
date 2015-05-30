package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;

public class Instance {

	public Instance() {

	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Game Screen", 10, 10);
	}

}
