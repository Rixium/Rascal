package com.bourneless.roguelike.map.tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;

public class Tile {

	public static final int size = 64;

	private Vector2 pos;
	private int tileType;
	private int tileClass; // Whether the tile is Wall or Floor
	private int tileX;
	private int tileY;
	private boolean visible;
	private boolean beenSeen = false;
	private int layer = 0;
	private int entityLayer = 0;
	private boolean room = false;

	private boolean starting = false;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private boolean hasEntity = false;

	private boolean used = false;

	private boolean passable; // If the tile can be used by Player and Entities.

	public Tile(Vector2 pos, int tileType, int tileClass, int tileX, int tileY,
			int layer) {
		this.pos = pos;
		this.tileType = tileType;
		this.tileClass = tileClass;
		this.tileX = tileX;
		this.tileY = tileY;
		this.layer = layer;

		if (tileClass == TileClass.WALL) {
			setPassable(false);
		} else {
			setPassable(true);
		}
	}

	public void update() {

	}

	public void paint(Graphics2D g, int xOffset, int yOffset) {
		if (tileClass == TileClass.FLOOR) {
			g.drawImage(Main.resourceLoader.tiles[tileType], pos.x + xOffset,
					pos.y + yOffset, null);
		}
		if (tileClass == TileClass.WALL) {
			g.drawImage(Main.resourceLoader.wallTiles[tileType], pos.x
					+ xOffset, pos.y + yOffset, null);
		}

		if (visible) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.0f));
		} else {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1));
			if (beenSeen) {
				g.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.8f));
			}
		}

		g.drawImage(Main.resourceLoader.fog, pos.x + xOffset, pos.y + yOffset,
				null);

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}

	public void paintEntity(Graphics2D g) {
		if (visible) {
			for (Entity entity : entities) {
				entity.paint(g);
			}
		}
	}

	public Vector2 getPos() {
		return this.pos;
	}

	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public int getTileClass() {
		return this.tileClass;
	}

	public int getTileX() {
		return this.tileX;
	}

	public int getTileY() {
		return this.tileY;
	}

	public void setVisible(boolean bool) {
		this.visible = bool;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setSeen() {
		this.beenSeen = true;
	}

	public int getTileType() {
		return this.tileType;
	}

	public void addEntity(Entity entity) {
		this.hasEntity = true;
		if (entity.getType() == EntityType.PLAYER) {
			this.layer = 1;
			this.passable = false;
			entityLayer = 3;
		} else if (entity.getType() == EntityType.ENEMY) {
			this.layer = 1;
			this.passable = false;
			entityLayer = 3;
		} else {
			this.layer = 1;
		}
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
		if (entity.getType() == EntityType.PLAYER && entities.size() > 0) {
			this.layer = 1;
			this.passable = true;
		} else if (entity.getType() == EntityType.ENEMY && entities.size() > 0) {
			this.passable = true;
		} else if (entities.size() == 0) {
			hasEntity = false;
			passable = true;
			entityLayer = 0;
			this.layer = 0;
		} else {
			passable = true;
		}

	}

	public int getLayer() {
		return this.layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public boolean hasEntity() {
		return this.hasEntity;
	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}

	public int getEntityLayer() {
		return this.entityLayer;
	}

	public void setTileX(int x) {
		this.tileX = x;
		for (Entity entity : entities) {
			entity.setX(this.pos.x);
		}
	}

	public void setTileY(int y) {
		this.tileY = y;
		for (Entity entity : entities) {
			entity.setY(this.pos.y);
		}
	}

	public void setTileType(int type) {
		this.tileType = type;
	}

	public void setTileClass(int tileclass) {
		this.tileClass = tileclass;
	}

	public void setUsed(boolean bool) {
		this.used = bool;
	}

	public boolean getUsed() {
		return this.used;
	}

	public boolean getRoom() {
		return this.room;
	}

	public void setRoom(boolean bool) {
		this.room = bool;
	}

	public boolean getSeen() {
		return this.beenSeen;
	}

	public boolean getStarting() {
		return this.starting;
	}

	public void setStarting() {
		this.starting = true;
	}
}
