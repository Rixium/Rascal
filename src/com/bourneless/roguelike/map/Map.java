package com.bourneless.roguelike.map;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.TileType;

public class Map {

	private Tile[][] tiles = new Tile[50][50];
	private Room room;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Random random = new Random();

	private int xOffset;
	private int yOffset;

	public Map() {
		createMap();
	}

	public void createMap() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(new Vector2((i * Tile.size),
						(j * Tile.size)), TileType.WOOD_FLOOR, TileClass.FLOOR,
						i, j, 0);
			}
		}

		room = new Room(this);
		int randomPos = random.nextInt(tiles.length * tiles.length);
		while (randomPos > tiles.length - room.getTiles().length) {
			randomPos = random.nextInt(tiles.length * tiles.length);
		}
		if (randomPos < tiles.length - room.getTiles().length) {
			for (int i = 0; i < room.getTiles().length; i++) {
				for (int j = 0; j < room.getTiles()[i].length; j++) {
					System.out.println("adding tile");
					tiles[i + randomPos][j] = room.getTiles()[i][j];
					tiles[i + randomPos][j].setTileX(i + randomPos);
					tiles[i + randomPos][j].setTileY(j);
					tiles[i + randomPos][j].setVisible(true);
					for (Entity entity : entities) {
						if (entity.getTile().getTileX() == room.getTiles()[i][j]
								.getTileX()
								&& entity.getTile().getTileY() == room
										.getTiles()[i][j].getTileY()) {
							entity.setTile(tiles[i + randomPos][j]);
						}
					}
				}
			}
		}
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		for (Entity entity : entities) {
			entity.update(xOffset, yOffset, this);
		}
	}

	public void paint(Graphics2D g) {
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

	public void keyPressed(KeyEvent e, Player p) {
		room.keyPressed(e, p);
	}

	public Room getRoom() {
		return this.room;
	}

	public ArrayList<Entity> getEntityList() {
		return this.entities;
	}

	public Tile[][] getTiles() {
		return this.tiles;
	}

}
