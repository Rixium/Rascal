package com.bourneless.roguelike.map.tile;

import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.player.Player;

public class Tile {

	public static final int size = 64;

	private Vector2 pos;
	private int tileType;
	private int tileClass; // Whether the tile is Wall or Floor
	
	private boolean passable; // If the tile can be used by Player and Entities.

	public Tile(Vector2 pos, int tileType, int tileClass) {
		this.pos = pos;
		this.tileType = tileType;
		this.tileClass = tileClass;
		if(tileClass == TileClass.WALL) {
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
			g.drawImage(Main.resourceLoader.wallTiles[tileType], pos.x + xOffset, pos.y - Main.resourceLoader.wallTiles[tileType].getHeight() + size + yOffset,
					null);
		}
		
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

}
