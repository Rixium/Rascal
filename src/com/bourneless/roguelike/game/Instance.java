package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.Minimap;

public class Instance {

	private Map map;
	private Minimap miniMap;
	
	private Player player;

	private int xOffset = 0;
	private int yOffset = 0;

	private int camSpeed = 4;

	public Instance() {
		map = new Map();
		player = map.getRoom().getPlayer();
		map.createMap();
		miniMap = new Minimap(map);
	}

	public void update() {
		if (Main.GAME_WIDTH / 2 - player.getPos().x - player.getXOff() < xOffset) {
			xOffset -= camSpeed;
		} else if (Main.GAME_WIDTH / 2 - player.getPos().x - player.getXOff() > xOffset) {
			xOffset += camSpeed;
		}
		if (Main.GAME_HEIGHT / 2 - player.getPos().y - player.getYOff() < yOffset) {
			yOffset -= camSpeed;
		} else if (Main.GAME_HEIGHT / 2 - player.getPos().y - player.getYOff() > yOffset) {
			yOffset += camSpeed;
		}

		map.update(xOffset, yOffset);
		player.update(xOffset, yOffset, map);
		miniMap.update(xOffset, yOffset);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		map.paint(g);
		miniMap.paint(g);
	}

	public void keyPressed(KeyEvent e) {
		map.keyPressed(e, player);
		player.keyPressed(e, map);
	}

	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

}
