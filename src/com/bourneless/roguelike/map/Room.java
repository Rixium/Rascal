package com.bourneless.roguelike.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.TileHex;
import com.bourneless.roguelike.map.tile.TileType;
import com.bourneless.roguelike.map.tile.WallTileType;

public class Room {

	private BufferedImage image;
	private Tile[][] tiles;
	private Random random = new Random();
	
	private Tile startTile;
	private int startTileX;
	private int startTileY;

	private int xOffset = 0;
	private int yOffset = 0;

	public Room() {
		this.image = Main.resourceLoader.rooms[random
				.nextInt(Main.resourceLoader.rooms.length)];
		this.tiles = new Tile[image.getWidth()][image.getHeight()];
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int c = image.getRGB(i, j);
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
				} else if (hex.matches(TileHex.LOWER_RED_WALL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), WallTileType.LOWER_RED_WALL,
							TileClass.WALL);
				} else if (hex.matches(TileHex.TOP_WALL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), WallTileType.TOP_WALL, TileClass.WALL);
				} else if (hex.matches(TileHex.START_TILE)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR, TileClass.FLOOR);
					startTile = tiles[i][j];
				}
			}
		}
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g, Player player) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getPos().x < Main.GAME_WIDTH + -xOffset
						&& tiles[i][j].getPos().y < Main.GAME_HEIGHT + -yOffset
								+ (Tile.size * 3)
						&& tiles[i][j].getPos().x > -xOffset - Tile.size
						&& tiles[i][j].getPos().y > -yOffset - Tile.size) {
					tiles[i][j].paint(g, player, xOffset, yOffset);
				}
			}
		}
	}

	public Tile[][] getTiles() {
		return this.tiles;

	}
	
	public Tile getStartTile() {
		return this.startTile;
	}
	
	public int getStartTileX() {
		return this.startTileX;
	}
	
	public int getStartTileY() {
		return this.startTileY;
	}

}
