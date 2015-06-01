package com.bourneless.roguelike.entity.door;

import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.map.tile.Tile;

public class Door extends Entity {

	private BufferedImage[] doorImage = Main.resourceLoader.door;
	private boolean open = false;

	public Door(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);
	}

	public void toggleState() {
		open = !open;

		if (open == true) {
			this.image = doorImage[1];
		} else {
			this.image = doorImage[0];
		}
	}

	public boolean getState() {
		return open;
	}

}
