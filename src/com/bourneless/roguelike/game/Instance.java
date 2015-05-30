package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.player.Player;
import com.bourneless.roguelike.map.Map;

public class Instance {

	Map map;

	Player p;

	public Instance() {
		map = new Map();
		p = new Player(map.getTiles()[5][5], Main.resourceLoader.player[0], 5,
				5);
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Game Screen", 10, 10);

		map.paint(g);
		p.paint(g);
	}

	public void keyPressed(KeyEvent e) {

	}

}
