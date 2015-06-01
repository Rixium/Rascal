package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.FieldOfView;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.Map;

public class Instance {

	private Map map;

	private Player player;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	private int camSpeed = 4;
	public Instance() {
		map = new Map();
		player = new Player(map.getRoom().getStartTile(), Main.resourceLoader.player[0], map.getRoom().getStartTileX(),
				map.getRoom().getStartTileY());
	}

	public void update() {
		if(Main.GAME_WIDTH / 2 - player.getPos().x - player.getXOff() < xOffset) {
			xOffset -= camSpeed;
		} else if(Main.GAME_WIDTH / 2 - player.getPos().x - player.getXOff() > xOffset) {
			xOffset += camSpeed;
		}
		if(Main.GAME_HEIGHT / 2 - player.getPos().y - player.getYOff() < yOffset) {
			yOffset -= camSpeed;
		} else if (Main.GAME_HEIGHT / 2 - player.getPos().y - player.getYOff() > yOffset) {
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
