package com.bourneless.roguelike.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.TileHex;
import com.bourneless.roguelike.map.tile.TileType;
import com.bourneless.roguelike.map.tile.WallTileType;

public class Map {

	private BufferedImage room;
	private Tile[][] tiles;

	public Map() {
		room = Main.resourceLoader.rooms[0];
		tiles = new Tile[room.getWidth()][room.getHeight()];
		createMap();
	}

	public void createMap() {
		for (int i = 0; i < room.getWidth(); i++) {
			for (int j = 0; j < room.getHeight(); j++) {
				int c = room.getRGB(i, j);
				Color color = new Color(c);
				String hex = "#"
						+ Integer.toHexString(color.getRGB()).substring(2);
				if (hex.matches(TileHex.RED_WALL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), WallTileType.RED_WALL, TileClass.WALL);
				} else if (hex.matches(TileHex.WOOD_FLOOR)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.WOOD_FLOOR, TileClass.FLOOR);
				} else if (hex.matches(TileHex.STONE_FLOOR)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR, TileClass.FLOOR);
				}
			}
		}
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].paint(g);
			}
		}
	}

	public Tile[][] getTiles() {
		return this.tiles;

	}
}
