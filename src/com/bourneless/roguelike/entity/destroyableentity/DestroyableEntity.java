package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.Hit;
import com.bourneless.roguelike.map.tile.Tile;

public class DestroyableEntity extends Entity {

	protected int health = 100;
	protected boolean broken = false;

	public DestroyableEntity(Tile tile, BufferedImage image, int type) {
		super(tile, image, type);
	}

	public void hit(int h) {
		this.health -= h;

		instance.getHits().add(
				new Hit(h, new Vector2(pos.x + image.getWidth() / 2, pos.y)));

		if (h > 0) {
			Main.resourceLoader.playClip(Main.resourceLoader.hitSound, 1f,
					false);
		}
		if (health <= 0) {
			Main.resourceLoader.playClip(Main.resourceLoader.breakSound, 1f,
					false);
			this.tile.setPassable(true);
			broken = true;
			passable = true;
		}
	}
}
