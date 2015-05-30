package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.player.Player;
import com.bourneless.roguelike.map.Map;

public class Instance {

	Map map;

	Player player;

	public Instance() {
		map = new Map();
		player = new Player(map.getTiles()[6][6], Main.resourceLoader.player[0], 6,
				6);
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Game Screen", 10, 10);

		map.paint(g, player);
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e, map);
	}

}
