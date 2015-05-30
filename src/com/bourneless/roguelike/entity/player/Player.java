package com.bourneless.roguelike.entity.player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Player extends Entity {

	public Player(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);
		type = EntityType.PLAYER;
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x, pos.y - image.getHeight() / 2, null);
	}

	public void keyPressed(KeyEvent e, Map map) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 65) {
			// A Key
			if (map.getTiles()[(pos.x / 64) - 1][(pos.y / 64)].isPassable()) {
				if (this.image.equals(Main.resourceLoader.player[1])) {
					this.pos.x -= 64;
					this.tile = map.getTiles()[pos.x / 64][pos.y / 64];
				} else {
					this.image = Main.resourceLoader.player[1];
				}
			}
		} else if (e.getKeyCode() == 68) {
			// D Key
			if (map.getTiles()[(pos.x / 64) + 1][(pos.y / 64)].isPassable()) {
				if (this.image.equals(Main.resourceLoader.player[2])) {
					this.pos.x += 64;
					this.tile = map.getTiles()[pos.x / 64][pos.y / 64];
				} else {
					this.image = Main.resourceLoader.player[2];
				}
			}
		}

		if (e.getKeyCode() == 87) {
			// W Key
			if (map.getTiles()[(pos.x / 64)][(pos.y / 64) - 1].isPassable()) {
				if (this.image.equals(Main.resourceLoader.player[3])) {
					this.pos.y -= 64;
					this.tile = map.getTiles()[pos.x / 64][pos.y / 64];
				} else {
					this.image = Main.resourceLoader.player[3];
				}
			}
		} else if (e.getKeyCode() == 83) {
			// S Key
			if (map.getTiles()[(pos.x / 64)][(pos.y / 64) + 1].isPassable()) {
				if (this.image.equals(Main.resourceLoader.player[0])) {
					this.pos.y += 64;
					this.tile = map.getTiles()[pos.x / 64][pos.y / 64];
				} else {
					this.image = Main.resourceLoader.player[0];
				}
			}
		}
	}
}
