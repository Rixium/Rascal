package com.bourneless.roguelike.entity.livingentity.mob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.livingentity.LivingEntity;
import com.bourneless.roguelike.map.tile.Tile;

public class Mob extends LivingEntity {

	protected int experience = 10;

	public Mob(Tile tile, BufferedImage image) {
		super(tile, image, EntityType.ENEMY);
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g) {
		if (!this.dead) {
			g.drawImage(image, pos.x + xOffset, pos.y - image.getHeight() / 2
					+ yOffset, null);
		}
	}

	public boolean getDead() {
		return this.dead;
	}

	public int getExperience() {
		return this.experience;
	}

}
