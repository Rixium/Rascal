package com.bourneless.roguelike.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.FieldOfView;
import com.bourneless.roguelike.entity.destroyableentity.Door;
import com.bourneless.roguelike.entity.livingentity.player.Player;
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

	private Player player;
	
	private boolean firstIteration = true;

	private FieldOfView fOV = new FieldOfView();

	public Room(Map map) {
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
							* Tile.size), WallTileType.RED_WALL,
							TileClass.WALL, i, j, 0);
				} else if (hex.matches(TileHex.WOOD_FLOOR)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.WOOD_FLOOR, TileClass.FLOOR,
							i, j, 0);
				} else if (hex.matches(TileHex.STONE_FLOOR)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR,
							TileClass.FLOOR, i, j, 0);
				} else if (hex.matches(TileHex.LOWER_RED_WALL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), WallTileType.LOWER_RED_WALL,
							TileClass.WALL, i, j, 0);
				} else if (hex.matches(TileHex.TOP_WALL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), WallTileType.TOP_WALL,
							TileClass.WALL, i, j, 3);
				} else if (hex.matches(TileHex.START_TILE)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR,
							TileClass.FLOOR, i, j, 0);
					player = new Player(tiles[i][j], Main.resourceLoader.player[0]);
					tiles[i][j].setLayer(2);
					tiles[i][j].addEntity(player);
				} else if (hex.matches(TileHex.STONE_FLOOR_DEC_1)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR_DEC_1,
							TileClass.FLOOR, i, j, 0);
				} else if (hex.matches(TileHex.STONE_FLOOR_DEC_2_TL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR_DEC_2_TL,
							TileClass.FLOOR, i, j, 0);
				} else if (hex.matches(TileHex.STONE_FLOOR_DEC_2_TR)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR_DEC_2_TR,
							TileClass.FLOOR, i, j, 0);
				} else if (hex.matches(TileHex.STONE_FLOOR_DEC_2_BL)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR_DEC_2_BL,
							TileClass.FLOOR, i, j, 0);
				} else if (hex.matches(TileHex.STONE_FLOOR_DEC_2_BR)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR_DEC_2_BR,
							TileClass.FLOOR, i, j, 0);
				} else if (hex.matches(TileHex.DOOR_TILE)) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), TileType.STONE_FLOOR,
							TileClass.FLOOR, i, j, 0);
					System.out.println("adding door");
					Door door = new Door(tiles[i][j],
							Main.resourceLoader.door[0]);
					tiles[i][j].addEntity(door);
					map.getEntityList().add(door);
				}
			}
		}
	}

	public void keyPressed(KeyEvent e, Player p) {
		if (e.getKeyCode() == 69) {
			// E Pressed
		}
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g, Player player, Map map) {
		int iteration = 0;
		while (iteration < 4) {
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (tiles[i][j].getPos().x < Main.GAME_WIDTH + -xOffset
							&& tiles[i][j].getPos().y < Main.GAME_HEIGHT
									+ -yOffset + (Tile.size * 3)
							&& tiles[i][j].getPos().x > -xOffset - Tile.size
							&& tiles[i][j].getPos().y > -yOffset - Tile.size) {
						if (tiles[i][j].getLayer() == 0 && iteration == 0) {
							tiles[i][j].paint(g, xOffset, yOffset);
						} else if (tiles[i][j].getLayer() == 1
								&& iteration == 1) {
							tiles[i][j].paint(g, xOffset, yOffset);
						} else if (tiles[i][j].getEntityLayer() == 3
								&& iteration == 3) {
							tiles[i][j].paintEntity(g);
						} else if (tiles[i][j].getLayer() == 4
								&& iteration == 4) {
							tiles[i][j].paint(g, xOffset, yOffset);
						}
					}
				}

			}
			iteration++;
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

	public Player getPlayer() {
		return this.player;
	}
}
