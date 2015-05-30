package com.bourneless.roguelike.map.tile;

import java.awt.Graphics2D;

import com.bourneless.engine.math.Vector2;

public class Tile {

	private Vector2 pos;
	
	public Tile(Vector2 pos) {
		this.pos = pos;
	}

	public void update() {

	}

	public void paint(Graphics2D g) {

	}
	
	public Vector2 getPos() {
		return this.pos;
	}

}
