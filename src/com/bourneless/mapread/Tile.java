package com.bourneless.mapread;

import java.awt.Color;
import java.awt.Graphics2D;

import com.bourneless.engine.math.Vector2;

public class Tile {

	public Vector2 pos;
	public int type;
	private int renderX;
	private int renderY;

	public Tile(Vector2 pos, int type) {
		this.pos = pos;
		this.type = type;
	}

	public void update(int renderX, int renderY) {
		this.renderX = renderX;
		this.renderY = renderY;
	}

	public void paint(Graphics2D g) {
		if (type == TileType.BLOCK) {
			g.setColor(Color.CYAN);
			g.fillRect(pos.x + renderX, pos.y + renderY, 32, 32);
		} else if (type == TileType.FREE) {
			g.setColor(Color.blue);
			g.fillRect(pos.x + renderX, pos.y + renderY, 32, 32);
		}
	}
}
