package com.bourneless.roguelike.map;

import java.awt.Graphics2D;

import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;

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
