package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;

public class DestroyableEntity extends Entity {

	protected int health = 100;
	protected boolean broken = false;

	public DestroyableEntity(Tile tile, BufferedImage image, int type) {
		super(tile, image, type);
	}

	public void hit(int h) {
		this.health -= h;
		if (h > 0) {
			Main.resourceLoader.playClip(Main.resourceLoader.hitSound, 1f,
					false);
		}
		if (health <= 0) {
			Main.resourceLoader.playClip(Main.resourceLoader.breakSound, 1f,
					false);
			broken = true;
			passable = true;
		}
	}

}
