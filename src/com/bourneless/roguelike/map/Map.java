package com.bourneless.roguelike.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.TileHex;
import com.bourneless.roguelike.map.tile.TileType;
import com.bourneless.roguelike.map.tile.WallTileType;

public class Map {

	private Tile[][] tiles;
	
	private Room room;

	public Map() {
		room = new Room();
	}

	public void update(int xOffset, int yOffset) {
		room.update(xOffset, yOffset);
	}

	public void paint(Graphics2D g, Player player) {
		room.paint(g, player);
	}

	public Room getRoom() {
		return this.room;
	}

}
