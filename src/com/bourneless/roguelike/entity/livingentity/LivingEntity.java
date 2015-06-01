package com.bourneless.roguelike.entity.livingentity;

import java.awt.image.BufferedImage;

import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.map.tile.Tile;

public class LivingEntity extends Entity{

	public LivingEntity(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);
	}

}
