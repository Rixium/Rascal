package com.bourneless.roguelike.entity.livingentity;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.map.tile.Tile;

public class LivingEntity extends Entity {

	protected int health = 100;
	protected boolean dead = false;
	protected Random random = new Random();

	public LivingEntity(Tile tile, BufferedImage image, int type) {
		super(tile, image, type);
	}

	public int getHealth() {
		return this.health;
	}
}
