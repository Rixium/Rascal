package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;

public class Instance {

	Player player = new Player(new Tile(new Vector2(200, 200)),
			Main.resourceLoader.player, 200, 200);

	public Instance() {

	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Game Screen", 10, 10);

		player.paint(g);
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

}
