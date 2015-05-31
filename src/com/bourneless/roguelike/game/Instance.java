package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.player.Player;
import com.bourneless.roguelike.map.Map;

public class Instance {

	private Map map;

	private Player player;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	private int camSpeed = 4;

	public Instance() {
		map = new Map();
		player = new Player(map.getRoom().getTiles()[6][6], Main.resourceLoader.player[0], 6,
				6);
	}

	public void update() {
		if(Main.GAME_WIDTH / 2 - player.getPos().x < xOffset) {
			xOffset -= camSpeed;
		} else if(Main.GAME_WIDTH / 2 - player.getPos().x > xOffset) {
			xOffset += camSpeed;
		}
		if(Main.GAME_HEIGHT / 2 - player.getPos().y < yOffset) {
			yOffset -= camSpeed;
		} else if (Main.GAME_HEIGHT / 2 - player.getPos().y > yOffset) {
			yOffset += camSpeed;
		}
		
		map.update(xOffset, yOffset);
		player.update(xOffset, yOffset, map);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("Game Screen", 10, 10);

		map.paint(g, player);
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e, map);
	}
	
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

}
