package com.bourneless.roguelike.map.tile;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.player.Player;

public class Tile {

	public static final int size = 64;

	private Vector2 pos;
	private int tileType;
	private int tileClass; // Whether the tile is Wall or Floor

	private boolean passable; // If the tile can be used by Player and Entities.

	private Rectangle rect;

	public Tile(Vector2 pos, int tileType, int tileClass) {
		this.pos = pos;
		this.tileType = tileType;
		this.tileClass = tileClass;
		if (tileClass == TileClass.FLOOR) {
			this.rect = new Rectangle(pos.x, pos.y, size, size);
		} else {
			this.rect = new Rectangle(pos.x, pos.y - Main.resourceLoader.wallTiles[tileType].getHeight() + size, size, size * 3);
		}

		if (tileClass == TileClass.WALL) {
			setPassable(false);
		} else {
			setPassable(true);
		}
	}

	public void update() {

	}

	public void paint(Graphics2D g, Player player, int xOffset, int yOffset) {
		if (tileClass == TileClass.FLOOR) {
			g.drawImage(Main.resourceLoader.tiles[tileType], pos.x + xOffset, pos.y + yOffset,
					null);
			if(player.getTile().getPos() == this.pos) {
				player.paint(g);
			}
		} else if (tileClass == TileClass.WALL) {
			if(player.getTile().getRect().intersects(this.rect)) {
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			}
			g.drawImage(Main.resourceLoader.wallTiles[tileType], pos.x + xOffset, pos.y - Main.resourceLoader.wallTiles[tileType].getHeight() + size + yOffset,
					null);
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}

	public Vector2 getPos() {
		return this.pos;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public int getTileClass() {
		return this.tileClass;
	}

	public Rectangle getRect() {
		return this.rect;
	}

}
