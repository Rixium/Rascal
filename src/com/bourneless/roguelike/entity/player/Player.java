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

	private int lastKey;
	
	public Player(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);
		type = EntityType.PLAYER;
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x + xOffset, pos.y - image.getHeight() / 2
				+ yOffset, null);
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void keyPressed(KeyEvent e, Map map) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 65 && lastKey != 65) {
			// A Key
			if (this.image.equals(Main.resourceLoader.player[1])) {
				if (map.getRoom().getTiles()[(pos.x / 64) - 1][(pos.y / 64)]
						.isPassable()) {
					this.pos.x -= 64;
					this.tile = map.getRoom().getTiles()[pos.x / 64][pos.y / 64];
				}
			} else {
				this.image = Main.resourceLoader.player[1];
			}
			lastKey = 65;
		} else if (e.getKeyCode() == 68 && lastKey != 68) {
			// D Key
			if (this.image.equals(Main.resourceLoader.player[2])) {
				if (map.getRoom().getTiles()[(pos.x / 64) + 1][(pos.y / 64)]
						.isPassable()) {
					this.pos.x += 64;
					this.tile = map.getRoom().getTiles()[pos.x / 64][pos.y / 64];
				}
			} else {
				this.image = Main.resourceLoader.player[2];
			}
			lastKey = 68;
		}

		if (e.getKeyCode() == 87 && lastKey != 87) {
			// W Key
			if (this.image.equals(Main.resourceLoader.player[3])) {
				if (map.getRoom().getTiles()[(pos.x / 64)][(pos.y / 64) - 1]
						.isPassable()) {
					this.pos.y -= 64;
					this.tile = map.getRoom().getTiles()[pos.x / 64][pos.y / 64];
				}
			} else {
				this.image = Main.resourceLoader.player[3];
			}
			lastKey = 87;
		} else if (e.getKeyCode() == 83 && lastKey != 83) {
			// S Key
			if (this.image.equals(Main.resourceLoader.player[0])) {
				if (map.getRoom().getTiles()[(pos.x / 64)][(pos.y / 64) + 1]
						.isPassable()) {
					this.pos.y += 64;
					this.tile = map.getRoom().getTiles()[pos.x / 64][pos.y / 64];
				}
			} else {
				this.image = Main.resourceLoader.player[0];
			}
			lastKey = 83;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		lastKey = 0;
	}

}
