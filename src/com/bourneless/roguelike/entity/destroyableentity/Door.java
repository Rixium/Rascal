package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;

public class Door extends DestroyableEntity {

	private boolean side = false;

	public Door(Tile tile, BufferedImage[] door, boolean side) {
		super(tile, Main.resourceLoader.door[0], EntityType.BREAKABLE);
		this.solid = true;
		this.side = side;
		this.name = "door";
	}

	public void paint(Graphics2D g) {
		if (broken) {

			if (!this.tile.isPassable()) {
				this.solid = false;
				this.tile.setPassable(true);
				this.passable = true;
			}

			if (!side) {
				g.drawImage(
						Main.resourceLoader.door[1],
						pos.x + xOffset,
						pos.y + yOffset
								- Main.resourceLoader.door[1].getHeight() / 2,
						null);
			} else {
				g.drawImage(Main.resourceLoader.sideDoor[1], pos.x + xOffset,
						pos.y + yOffset, null);
			}
		} else {
			if (!side) {
				g.drawImage(
						Main.resourceLoader.door[0],
						pos.x + xOffset,
						pos.y + yOffset
								- Main.resourceLoader.door[0].getHeight() / 2,
						null);
			} else {
				g.drawImage(Main.resourceLoader.sideDoor[0], pos.x + xOffset,
						pos.y + yOffset, null);
			}
		}
	}

}
