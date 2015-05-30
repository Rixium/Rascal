package com.bourneless.roguelike.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.map.tile.Tile;

public class Entity {

	private Vector2 pos;
	private Tile tile;
	private BufferedImage image;

	protected int type;

	protected int tileX;
	protected int tileY;

	public Entity(Tile tile, BufferedImage image, int tileX, int tileY) {
		this.pos = new Vector2(tile.getPos().x, tile.getPos().y);
		this.image = image;
		this.tile = tile;

		this.tileX = tileX;
		this.tileY = tileY;
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x, pos.y, null);
	}

	public Tile getTile() {
		return this.tile;
	}

	public int getTileX() {
		return this.tileX;
	}

	public int getTileY() {
		return this.tileY;
	}

}
