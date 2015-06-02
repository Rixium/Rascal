package com.bourneless.roguelike.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Entity {

	protected Vector2 pos;
	protected Tile tile;
	protected BufferedImage image;

	protected int type;

	protected int tileX;
	protected int tileY;

	protected int xOffset;
	protected int yOffset;

	protected boolean solid;

	protected boolean passable;
	protected Map map;

	protected Rectangle rect;

	public Entity(Tile tile, BufferedImage image, int type) {
		this.pos = new Vector2(tile.getPos().x, tile.getPos().y);
		this.image = image;
		this.tile = tile;
		this.type = type;
	}

	public void update(int xOffset, int yOffset, Map map) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.map = map;
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x + xOffset, pos.y + yOffset - image.getHeight()
				/ 2, null);
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

	public Vector2 getPos() {
		return pos;
	}

	public boolean getPassable() {
		return this.passable;
	}

	public int getType() {
		return this.type;
	}

	public boolean getSolid() {
		return this.solid;
	}

	public void setTile(Tile tile) {
		this.pos = new Vector2(tile.getPos().x, tile.getPos().y);
		this.tile = tile;
		this.tileX = tile.getTileX();
		this.tileY = tile.getTileY();
	}
	
	public void setX(int x) {
		this.pos.x = x;
	}
	
	public void setY(int y) {
		this.pos.y = y;
	}

}
