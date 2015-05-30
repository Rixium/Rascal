package com.bourneless.roguelike.entity.player;

import java.awt.image.BufferedImage;

import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;

public class Player extends Entity{

	public Player(Tile tile, BufferedImage image) {
		super(tile, image);
		type = EntityType.PLAYER;
	}

}
