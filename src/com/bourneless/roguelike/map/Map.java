package com.bourneless.roguelike.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.destroyableentity.Chest;
import com.bourneless.roguelike.entity.destroyableentity.Door;
import com.bourneless.roguelike.entity.destroyableentity.ExitDoor;
import com.bourneless.roguelike.entity.livingentity.mob.Mob;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.game.Instance;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.TileType;
import com.bourneless.roguelike.map.tile.WallTileType;
import com.bourneless.roguelike.screen.LoadScreen;

public class Map {

	private Tile[][] tiles = new Tile[103][103];
	private Room room;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Random random = new Random();

	private int xOffset;
	private int yOffset;

	// Lower is for higher spawn chance.
	private int mobSpawnRarity = 20;
	private int chestRarity = 30;

	private ArrayList<Room> rooms = new ArrayList<Room>();

	private Player player;
	private Instance instance;

	public Map(Instance instance) {
		this.instance = instance;
	}

	public void generate() {

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(new Vector2((i * Tile.size),
						(j * Tile.size)), WallTileType.TOP_WALL,
						TileClass.WALL, i, j, 0);
			}
		}

		int roomCount = 500;

		int x = 1;
		int y = 1;
		int rowLength = 0;

		for (int i = 0; i < roomCount; i++) {

			int roomTry = 20000;
			Room room = null;
			int size = 0;
			int size2 = 0;
			boolean canSpawn = true;

			if (rooms.size() == 0) {
				size = 10;
				size2 = 10;

				size *= Tile.size;
				size2 *= Tile.size;

				x *= Tile.size;
				y *= Tile.size;

				room = new Room(x, y, size, size2);

				rooms.add(new Room(room.getRect().x, room.getRect().y, room
						.getRect().width, room.getRect().height));

				if (x / Tile.size + size / Tile.size < tiles.length) {
					x += size;
				}
			}

			for (int j = 0; j < roomTry; j++) {
				size = 10;
				size2 = 10;

				size *= Tile.size;
				size2 *= Tile.size;

				if (x / Tile.size + size / Tile.size < tiles.length
						&& y / Tile.size + size2 / Tile.size < tiles.length) {
					room = new Room(x, y, size, size2);

					boolean checkRooms = true;

					tryingRoom: if (checkRooms) {
						for (Room r : rooms) {
							if (r.getRect().intersects(room.getRect())
									|| r.getRect().contains(room.getRect())
									|| room.getRect().contains(r.getRect())
									|| room.getRect().intersects(r.getRect())) {
								canSpawn = false;
								checkRooms = false;
								break tryingRoom;
							}
						}
					}

					if (canSpawn) {
						if (x / Tile.size + size / Tile.size < tiles.length
								&& y / Tile.size + size2 / Tile.size < tiles.length) {
							Room newRoom = new Room(room.getRect().x,
									room.getRect().y, room.getRect().width,
									room.getRect().height);
							rooms.add(newRoom);
							newRoom = null;

							if (x / Tile.size + size / Tile.size < tiles.length - 1) {
								x += size;
							}
						}

						break;
					}
				}
			}

			if (x / Tile.size + size / Tile.size > tiles.length - 1) {
				rowLength = rooms.size();
				x = 1 * Tile.size;
				y += rooms.get(rooms.size() - rowLength).getRect().getHeight();
			}

		}

		fillRects();

	}

	public void fillRects() {
		for (Room r : rooms) {
			for (int i = (int) r.getRect().getX() / Tile.size; i < r.getRect()
					.getX() / Tile.size + r.getRect().width / Tile.size; i++) {
				for (int j = (int) r.getRect().getY() / Tile.size; j < r
						.getRect().getY()
						/ Tile.size
						+ r.getRect().height
						/ Tile.size; j++) {
					tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
							* Tile.size), r.getTileStyle(), TileClass.FLOOR, i,
							j, 0);
				}
			}

		}

		for (Room r : rooms) {
			for (int i = (int) r.getRect().getX() / Tile.size; i < r.getRect()
					.getX() / Tile.size + r.getRect().width / Tile.size; i++) {
				for (int j = (int) r.getRect().getY() / Tile.size; j < r
						.getRect().getY()
						/ Tile.size
						+ r.getRect().height
						/ Tile.size; j++) {
					if (i == (int) r.getRect().getX() / Tile.size
							|| j == (int) r.getRect().getY() / Tile.size
							|| i == (int) r.getRect().getX() / Tile.size
									+ r.getRect().width / Tile.size
							|| j == (int) r.getRect().getY() / Tile.size
									+ r.getRect().height / Tile.size) {
						tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
								* Tile.size), WallTileType.TOP_WALL,
								TileClass.WALL, i, j, 0);
					}
				}
			}
		}

		for (Room r : rooms) {
			for (int i = (int) r.getRect().getX() / Tile.size; i < r.getRect()
					.getX() / Tile.size + r.getRect().width / Tile.size; i++) {
				for (int j = (int) r.getRect().getY() / Tile.size; j < r
						.getRect().getY() / 64 + r.getRect().height / Tile.size; j++) {
					if (i == (int) r.getRect().getX() / Tile.size
							|| j == (int) r.getRect().getY() / Tile.size
							|| i == (int) r.getRect().getX() / Tile.size
									+ r.getRect().width / Tile.size
							|| j == (int) r.getRect().getY() / Tile.size
									+ r.getRect().height / Tile.size) {
						tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
								* Tile.size), WallTileType.TOP_WALL,
								TileClass.WALL, i, j, 0);
					}
				}
			}
		}

		for (Room r : rooms) {
			for (int i = (int) r.getRect().getX() / Tile.size; i < r.getRect()
					.getX() / Tile.size + r.getRect().width / Tile.size; i++) {
				for (int j = (int) r.getRect().getY() / Tile.size; j < r
						.getRect().getY()
						/ Tile.size
						+ r.getRect().height
						/ Tile.size; j++) {
					if (i == (int) r.getRect().getX() / Tile.size
							|| j == (int) r.getRect().getY() / Tile.size
							|| i == (int) r.getRect().getX() / Tile.size
									+ r.getRect().width / Tile.size
							|| j == (int) r.getRect().getY() / Tile.size
									+ r.getRect().height / Tile.size) {
						tiles[i][j] = new Tile(new Vector2(i * Tile.size, j
								* Tile.size), WallTileType.TOP_WALL,
								TileClass.WALL, i, j, 0);
					}
				}
			}
		}

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (j == 0 || i == 0 || j == tiles.length - 1
						|| i == tiles.length - 1) {
					tiles[i][j].setTileClass(TileClass.WALL);
					tiles[i][j].setTileType(WallTileType.TOP_WALL);
					tiles[i][j].setPassable(false);
				}
			}
		}

		for (Room r : rooms) {
			while (!r.isHasEastDoor()) {
				if (r.getRect().getX() / Tile.size < 90) {
					for (int i = r.getRect().x / Tile.size; i <= r.getRect().x
							/ Tile.size + r.getRect().width / Tile.size; i++) {
						for (int j = r.getRect().y / Tile.size; j <= r
								.getRect().y
								/ Tile.size
								+ r.getRect().height
								/ Tile.size; j++) {
							if (i == r.getRect().x / Tile.size
									+ r.getRect().width / 64
									&& j > r.getRect().y / Tile.size
									&& j < r.getRect().y / Tile.size
											+ r.getRect().height / Tile.size) {
								if (random.nextInt(100) == 1
										&& !r.isHasEastDoor()) {
									Door door = new Door(tiles[i][j],
											Main.resourceLoader.door, true);
									tiles[i][j].setLayer(0);
									tiles[i][j].addEntity(door);
									tiles[i][j].setTileClass(TileClass.FLOOR);
									tiles[i][j].setTileType(TileType.TUNNEL);
									entities.add(door);
									r.setHasEastDoor(true);
								}
							}
						}
					}
				} else {
					r.setHasEastDoor(true);
				}
			}

			while (!r.isHasSouthDoor()) {
				if (r.getRect().getY() / Tile.size < 90) {
					for (int i = r.getRect().x / Tile.size; i <= r.getRect().x
							/ Tile.size + r.getRect().width / Tile.size; i++) {
						for (int j = r.getRect().y / Tile.size; j <= r
								.getRect().y
								/ Tile.size
								+ r.getRect().height
								/ Tile.size; j++) {
							if (j == r.getRect().y / Tile.size
									+ r.getRect().height / Tile.size
									&& i > r.getRect().x / Tile.size
									&& i < r.getRect().x / Tile.size
											+ r.getRect().width / Tile.size) {
								if (random.nextInt(100) == 1
										&& !r.isHasSouthDoor()) {
									Door door = new Door(tiles[i][j],
											Main.resourceLoader.door, false);
									tiles[i][j].setLayer(0);
									tiles[i][j].addEntity(door);
									tiles[i][j].setTileClass(TileClass.FLOOR);
									tiles[i][j].setTileType(TileType.TUNNEL);
									entities.add(door);
									r.setHasSouthDoor(true);
								}
							}
						}
					}
				} else {
					r.setHasSouthDoor(true);
				}
			}

		}
		addExit();

		populateMap();

		boolean hasPlayer = false;

		if (instance.getPlayer() == null) {
			hasPlayer = false;
		} else {
			hasPlayer = true;
		}

		int spawnPlayer = 0;

		if (hasPlayer) {
			boolean hasTile = false;

			while (!hasTile) {
				for (int i = 0; i < tiles.length; i++) {
					for (int j = 0; j < tiles[i].length; j++) {
						if (tiles[i][j] != null) {
							if (tiles[i][j].getTileClass() == TileClass.FLOOR
									&& tiles[i][j].getTileType() != TileType.TUNNEL) {
								spawnPlayer = random.nextInt(1000);
								if (spawnPlayer == 1 && !hasTile) {
									instance.getPlayer().setTile(tiles[i][j]);
									tiles[i][j].setLayer(0);
									tiles[i][j].addEntity(instance.getPlayer());
									this.player = instance.getPlayer();
									entities.add(instance.getPlayer());
									hasTile = true;
								}
							}
						}
					}
				}
			}
		}

		while (!hasPlayer) {
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (tiles[i][j] != null) {

						if (tiles[i][j].getTileClass() == TileClass.FLOOR
								&& tiles[i][j].getTileType() != TileType.TUNNEL) {
							spawnPlayer = random.nextInt(1000);
							if (spawnPlayer == 1 && !hasPlayer) {
								player = new Player(tiles[i][j],
										Main.resourceLoader.player[0]);
								tiles[i][j].setLayer(0);
								tiles[i][j].addEntity(player);
								entities.add(player);
								hasPlayer = true;
							}
						}
					}
				}
			}
		}

	}

	public void addExit() {
		boolean hasExit = false;
		while (!hasExit) {
			addingExit: for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[i].length; j++) {
					if (tiles[i][j].getTileClass() == TileClass.FLOOR) {
						if (!tiles[i][j].hasEntity()) {
							if (j + 1 < tiles[i].length) {
								if (tiles[i][j + 1].getTileClass() == TileClass.FLOOR) {
									int spawnExit = com.bourneless.engine.math.Random
											.getRandom(1000);
									if (spawnExit == 1) {
										ExitDoor exitDoor = new ExitDoor(
												tiles[i][j],
												Main.resourceLoader.levelExit);
										tiles[i][j].addEntity(exitDoor);
										hasExit = true;
										entities.add(exitDoor);
										tiles[i][j].setPassable(false);
										System.out.println("Exit at: "
												+ tiles[i][j].getTileX()
												+ " | "
												+ tiles[i][j].getTileY());
										break addingExit;
									}
								}
							}

						}
					}
				}
			}
		}
	}

	public void populateMap() {
		addChests();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getTileClass() == TileClass.FLOOR) {
					int spawnMonster = random.nextInt(mobSpawnRarity);
					if (spawnMonster == 1) {
						if (tiles[i][j].getEntities().isEmpty()) {
							Mob monster = new Mob(tiles[i][j],
									Main.resourceLoader.monsterImages[0], instance);
							tiles[i][j].setLayer(0);
							tiles[i][j].addEntity(monster);
							entities.add(monster);
						}
					}
				}
			}
		}
	}

	public void addChests() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getTileClass() == TileClass.FLOOR) {
					int spawnChest = random.nextInt(chestRarity);
					if (spawnChest == 1) {
						if (tiles[i][j - 1].getTileClass() == TileClass.WALL) {
							if (tiles[i][j].getEntities().isEmpty()) {
								Chest chest = new Chest(tiles[i][j],
										Main.resourceLoader.chest[0],
										EntityType.BREAKABLE);
								tiles[i][j].setLayer(0);
								tiles[i][j].addEntity(chest);
								entities.add(chest);
							}
						}
					}
				}
			}
		}
	}

	public void update(int xOffset, int yOffset, Instance instance) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		if (!instance.getCheat().hasStarted()) {
			for (Entity entity : entities) {
				if (entity.getType() != EntityType.PLAYER) {
					entity.update(xOffset, yOffset, this, instance);
					if (entity.getType() == EntityType.ENEMY) {
						Mob mob = (Mob) entity;
						if (mob.getDead()) {
							Main.game.getGameStats().totalKills++;
							player.getStats().addExperience(
									mob.getExperienceWorth());
							entities.remove(mob);
							mob.getTile().removeEntity(mob);
							break;
						}
					}
				}
			}
		}
		instance.setPlayerTurn(true);
	}

	public void paint(Graphics2D g) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getPos().x < Main.GAME_WIDTH + -xOffset
						&& tiles[i][j].getPos().y < Main.GAME_HEIGHT + -yOffset
								+ (Tile.size * 3)
						&& tiles[i][j].getPos().x > -xOffset - Tile.size
						&& tiles[i][j].getPos().y > -yOffset - Tile.size) {
					if (tiles[i][j].getLayer() == 0) {
						tiles[i][j].paint(g, xOffset, yOffset);
					} else if (tiles[i][j].getLayer() == 1) {
						tiles[i][j].paint(g, xOffset, yOffset);
					} else if (tiles[i][j].getLayer() == 3) {
						tiles[i][j].paint(g, xOffset, yOffset);
					}
				}
			}
		}

		for (int layer = 0; layer < tiles.length; layer++) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).getLayer() == layer - 1) {
					if (entities.get(i).getTile().isVisible()) {
						entities.get(i).paint(g);
					}
				}
			}
		}

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

	public Player getPlayer() {
		return this.player;
	}
}
