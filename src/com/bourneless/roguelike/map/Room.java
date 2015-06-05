package com.bourneless.roguelike.map;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;

public class Room {

	private BufferedImage image;
	private Tile[][] tiles;
	private Random random = new Random();

	private Tile startTile;
	private int startTileX;
	private int startTileY;

	private int size;

	private int tileType;

	private int xOffset = 0;
	private int yOffset = 0;

	private boolean hasEastDoor = false;
	private boolean hasSouthDoor = false;

	private boolean hasCorridor = false;

	private Player player;

	private Rectangle rect;

	public Room(int x, int y, int size, int size2) {
		rect = new Rectangle(x, y, size, size2);

		this.tileType = random.nextInt(Main.resourceLoader.tiles.length);
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

	public Rectangle getRect() {
		return this.rect;
	}

	public int getTileStyle() {
		return this.tileType;
	}

	public boolean getCorridor() {
		return this.hasCorridor;
	}

	public void setHasCorridor(boolean bool) {
		this.hasCorridor = bool;
	}

	public boolean isHasEastDoor() {
		return hasEastDoor;
	}

	public void setHasEastDoor(boolean hasEastDoor) {
		this.hasEastDoor = hasEastDoor;
	}

	public boolean isHasSouthDoor() {
		return hasSouthDoor;
	}

	public void setHasSouthDoor(boolean hasSouthDoor) {
		this.hasSouthDoor = hasSouthDoor;
	}

}
