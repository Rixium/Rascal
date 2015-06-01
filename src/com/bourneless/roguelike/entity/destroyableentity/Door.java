package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;

public class Door extends DestroyableEntity {

	public Door(Tile tile, BufferedImage image) {
		super(tile, Main.resourceLoader.door[0], EntityType.BREAKABLE);
		this.rect = new Rectangle(pos.x, pos.y, Main.resourceLoader.door[0].getWidth(), Main.resourceLoader.door[0].getHeight());
		this.solid = true;
	}
	
	public void paint(Graphics2D g) {
		if(broken) {
			g.drawImage(Main.resourceLoader.door[1], pos.x + xOffset, pos.y + yOffset - image.getHeight() / 2, null);
		} else {
			g.drawImage(Main.resourceLoader.door[0], pos.x + xOffset, pos.y + yOffset - image.getHeight() / 2, null);
		}
	}

}
