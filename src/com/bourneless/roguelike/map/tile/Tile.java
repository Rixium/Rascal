package com.bourneless.roguelike.map.tile;

import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;

public class Tile {

	public static final int size = 64;

	private Vector2 pos;
	private int tileType;

	public Tile(Vector2 pos, int tileType) {
		this.pos = pos;
		this.tileType = tileType;
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.drawImage(Main.resourceLoader.tiles[tileType], pos.x, pos.y, null);
	}

	public Vector2 getPos() {
		return this.pos;
	}

}
