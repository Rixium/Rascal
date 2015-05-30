package com.bourneless.roguelike.entity.player;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;

public class Player extends Entity {

	public Player(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);
		type = EntityType.PLAYER;
	}

	public void update() {

	}

	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 65) {

		}
	}
}
