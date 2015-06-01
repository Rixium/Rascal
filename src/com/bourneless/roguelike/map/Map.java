package com.bourneless.roguelike.map;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;

public class Map {

	private Tile[][] tiles;

	private Room room;
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public Map() {
		room = new Room(this);
	}

	public void update(int xOffset, int yOffset) {
		room.update(xOffset, yOffset);
		for(Entity entity : entities) {
			entity.update(xOffset, yOffset);
		}
	}

	public void paint(Graphics2D g, Player player) {
		room.paint(g, player, this);
	}

	public void keyPressed(KeyEvent e, Player p) {
		room.keyPressed(e, p);
	}

	public Room getRoom() {
		return this.room;
	}
	
	public ArrayList<Entity> getEntityList() {
		return this.entities;
	}

}
