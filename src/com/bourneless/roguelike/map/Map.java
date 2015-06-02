package com.bourneless.roguelike.map;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.destroyableentity.Door;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.WallTileType;

public class Map {

	private Tile[][] tiles = new Tile[60][60];
	private Room room;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Random random = new Random();
	private Room[] extraRooms = new Room[5];

	private int xOffset;
	private int yOffset;

	public Map() {
		room = new Room(this, 0);
	}

	public void createMap() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(new Vector2((i * Tile.size),
						(j * Tile.size)), WallTileType.TOP_WALL,
						TileClass.WALL, i, j, 0);
			}
		}

		int randomX = random.nextInt(tiles.length);
		int randomY = random.nextInt(tiles[randomX].length);

		while (randomX > tiles.length - room.getTiles().length
				|| randomY > tiles[randomX].length - room.getTiles()[0].length) {
			randomX = random.nextInt(tiles.length);
			randomY = random.nextInt(tiles.length);
		}

		for (int i = 0; i < room.getTiles().length; i++) {
			for (int j = 0; j < room.getTiles()[i].length; j++) {
				Tile tile = new Tile(new Vector2((randomX + i) * Tile.size,
						(randomY + j) * Tile.size),
						room.getTiles()[i][j].getTileType(),
						room.getTiles()[i][j].getTileClass(), randomX + i,
						randomY + j, room.getTiles()[i][j].getLayer());
				tiles[randomX + i][randomY + j] = tile;
				if (room.getTiles()[i][j].hasEntity()) {
					ArrayList<Entity> entities = room.getTiles()[i][j]
							.getEntities();
					for (Entity entity : entities) {
						entity.setTile(tile);
					}
				}

			}
		}
		boolean wallHasDoor = false;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getTileClass() == TileClass.WALL) {
					if (j < tiles[i].length - 1) {
						if (tiles[i][j + 1].getTileClass() == TileClass.FLOOR) {
							int spawnDoor = random.nextInt(20);
							if (spawnDoor == 1 && !wallHasDoor) {
								wallHasDoor = true;
								Door door = new Door(tiles[i][j],
										Main.resourceLoader.door[0]);
								tiles[i][j].addEntity(door);
								entities.add(door);
								tiles[i][j].setTileClass(TileClass.FLOOR);
								tiles[i][j - 1].setTileClass(TileClass.FLOOR);
								tiles[i][j - 1].setTileType(tiles[i][j + 1]
										.getTileType());
								tiles[i][j - 2].setTileClass(TileClass.FLOOR);
								tiles[i][j - 2].setTileType(tiles[i][j + 1]
										.getTileType());
								tiles[i][j - 1].setPassable(true);
								tiles[i][j - 2].setPassable(true);
								tiles[i][j].setTileType(tiles[i][j + 1]
										.getTileType());
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < extraRooms.length; i++) {
			int randomNum = 0;
			while (randomNum == 0) {
				randomNum = random.nextInt(Main.resourceLoader.rooms.length);
			}
			extraRooms[i] = new Room(this, 1);
		}
		for (int i = 0; i < extraRooms.length; i++) {
			createRoom(i);
		}

	}

	public void createRoom(int rm) {

		int randomX = random.nextInt(tiles.length);
		int randomY = random.nextInt(tiles[0].length);

		while (randomX > tiles.length - extraRooms[rm].getTiles().length
				|| randomY > tiles[randomX].length
						- extraRooms[rm].getTiles()[0].length || randomX == 0
				|| randomY == 0) {
			randomX = random.nextInt(tiles.length);
			randomY = random.nextInt(tiles.length);

			// Check if the room intersects another floor tile already placed.
			for (int i = 0; i < extraRooms[rm].getTiles().length; i++) {
				for (int j = 0; j < extraRooms[rm].getTiles()[i].length; j++) {
					if (randomX + i + 1 < tiles.length
							&& randomY + j + 1 < tiles[0].length
							&& randomY - 1 > 0 && randomX - 1 > 0) {
						if (tiles[randomX + i][randomY + j].getTileClass() != TileClass.WALL) {
							System.out.println("setting randomX Y");
							randomX = 0;
							randomY = 0;
						}
						if (randomX + i - 1 > 0) {
							if (tiles[randomX + i - 1][randomY + j]
									.getTileClass() != TileClass.WALL) {
								randomX = 0;
								randomY = 0;
							}
						}
						if (randomX + i + 1 < tiles.length) {
							if (tiles[randomX + i + 1][randomY + j]
									.getTileClass() != TileClass.WALL) {
								randomX = 0;
								randomY = 0;
							}
						}

						if (randomY + j + 1 > tiles.length) {
							if (tiles[randomX + i][randomY + j + 1]
									.getTileClass() != TileClass.WALL) {
								randomX = 0;
								randomY = 0;
							}
						}
						if (randomY + j - 1 > 0) {
							if (tiles[randomX + i][randomY + j - 1]
									.getTileClass() != TileClass.WALL) {
								randomX = 0;
								randomY = 0;
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < extraRooms[rm].getTiles().length; i++) {
			for (int j = 0; j < extraRooms[rm].getTiles()[i].length; j++) {
				Tile tile = new Tile(new Vector2((randomX + i) * Tile.size,
						(randomY + j) * Tile.size),
						extraRooms[rm].getTiles()[i][j].getTileType(),
						extraRooms[rm].getTiles()[i][j].getTileClass(), randomX
								+ i, randomY + j,
						room.getTiles()[i][j].getLayer());
				tiles[randomX + i][randomY + j] = tile;
				if (extraRooms[rm].getTiles()[i][j].hasEntity()) {
					ArrayList<Entity> entities = extraRooms[rm].getTiles()[i][j]
							.getEntities();
					for (Entity entity : entities) {
						entity.setTile(tile);
					}
				}

			}
		}
		boolean wallHasDoor = false;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getTileClass() == TileClass.WALL) {
					if (j < tiles[i].length - 1) {
						if (tiles[i][j + 1].getTileClass() == TileClass.FLOOR) {
							int spawnDoor = random.nextInt(20);
							if (spawnDoor == 1 && !wallHasDoor) {
								wallHasDoor = true;
								Door door = new Door(tiles[i][j],
										Main.resourceLoader.door[0]);
								tiles[i][j].addEntity(door);
								entities.add(door);
								tiles[i][j].setTileClass(TileClass.FLOOR);
								tiles[i][j - 1].setTileClass(TileClass.FLOOR);
								tiles[i][j - 1].setTileType(tiles[i][j + 1]
										.getTileType());
								tiles[i][j - 2].setTileClass(TileClass.FLOOR);
								tiles[i][j - 2].setTileType(tiles[i][j + 1]
										.getTileType());
								tiles[i][j - 1].setPassable(true);
								tiles[i][j - 2].setPassable(true);
								tiles[i][j].setTileType(tiles[i][j + 1]
										.getTileType());
							}
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
							tiles[i][j].paintEntity(g);
						} else if (tiles[i][j].getLayer() == 2
								&& iteration == 3) {
							tiles[i][j].paint(g, xOffset, yOffset);
							tiles[i][j].paintEntity(g);
						} else if (tiles[i][j].getLayer() == 3
								&& iteration == 3) {
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
