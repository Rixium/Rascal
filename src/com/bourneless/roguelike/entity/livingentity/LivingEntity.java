package com.bourneless.roguelike.entity.livingentity;

import java.awt.image.BufferedImage;

import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;

public class LivingEntity extends Entity{

	public LivingEntity(Tile tile, BufferedImage image, int type) {
		super(tile, image, EntityType.PLAYER);
	}

}
