package com.bourneless.roguelike.entity.door;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.map.tile.Tile;

public class Door extends Entity {

	private BufferedImage[] doorImage = Main.resourceLoader.door;
	private boolean open = false;
	private Rectangle rect;

	public Door(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);

		rect = new Rectangle(tile.getTileX(), tile.getTileY(),
				image.getWidth(), image.getHeight());

		//this.getTile().setPassable(false);
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x + xOffset, pos.y + yOffset, null);
	}

	public void toggleState() {
		open = !open;

		if (open == true) {
			this.image = doorImage[1];
			//this.getTile().setPassable(true);
		} else {
			this.image = doorImage[0];
			//this.getTile().setPassable(false);
		}
	}

	public boolean getState() {
		return open;
	}

	public Rectangle getRect() {
		return rect;
	}

}
