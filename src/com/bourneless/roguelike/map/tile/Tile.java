package com.bourneless.roguelike.map.tile;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;

public class Tile {

	public static final int size = 64;

	private Vector2 pos;
	private int tileType;
	private int tileClass; // Whether the tile is Wall or Floor
	private int tileX;
	private int tileY;
	private boolean visible;
	private boolean beenSeen = false;

	private boolean passable; // If the tile can be used by Player and Entities.

	public Tile(Vector2 pos, int tileType, int tileClass, int tileX, int tileY) {
		this.pos = pos;
		this.tileType = tileType;
		this.tileClass = tileClass;
		this.tileX = tileX;
		this.tileY = tileY;

		if (tileClass == TileClass.WALL) {
			setPassable(false);
		} else {
			setPassable(true);
		}
	}

	public void update() {

	}

	public void paint(Graphics2D g, Entity entity, int xOffset, int yOffset) {
		if (tileClass == TileClass.FLOOR) {
			g.drawImage(Main.resourceLoader.tiles[tileType], pos.x + xOffset,
					pos.y + yOffset, null);
			if (entity.getTile().getPos() == this.pos
					|| entity.getTile().getPos().x == this.pos.x - size
					|| entity.getTile().getPos().y == this.pos.y + size) {
				entity.paint(g);
			}
		}
		if (tileClass == TileClass.WALL) {
			if (entity.getTile().getPos() == this.pos
					|| entity.getTile().getPos().x == this.pos.x - size
					|| entity.getTile().getPos().y == this.pos.y + size) {
				entity.paint(g);
			}
			g.drawImage(Main.resourceLoader.wallTiles[tileType], pos.x
					+ xOffset, pos.y + yOffset, null);
		}

		int dx = entity.getTile().getTileX() - tileX;
		int dy = entity.getTile().getTileY() - tileY;
		int dist = (int) Math.sqrt(dx * dx + dy * dy);
		if (dist > 5 || dist < -5 || !visible) {
			if (dist == 6 || dist == -6) {
				if (visible) {
					g.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.5f));
				} else {
					g.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 1));
					if (beenSeen) {
						g.setComposite(AlphaComposite.getInstance(
								AlphaComposite.SRC_OVER, 0.8f));
					}
				}
			} else {
				g.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 1));
				if (beenSeen) {
					g.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.8f));
				}
			}

			g.drawImage(Main.resourceLoader.fog, pos.x + xOffset, pos.y
					+ yOffset, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1));
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
}
