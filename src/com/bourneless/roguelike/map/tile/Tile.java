package com.bourneless.roguelike.map.tile;

import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;

public class Tile {

	public static final int size = 64;

	private Vector2 pos;
	private int tileType;
	private int tileClass;

	public Tile(Vector2 pos, int tileType, int tileClass) {
		this.pos = pos;
		this.tileType = tileType;
		this.tileClass = tileClass;
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		if (tileClass == TileClass.FLOOR) {
			g.drawImage(Main.resourceLoader.tiles[tileType], pos.x, pos.y,
					null);
		} else if (tileClass == TileClass.WALL) {
			g.drawImage(Main.resourceLoader.wallTiles[tileType], pos.x, pos.y - Main.resourceLoader.wallTiles[tileType].getHeight() + size,
					null);
		}
	}

	public Vector2 getPos() {
		return this.pos;
	}

}
