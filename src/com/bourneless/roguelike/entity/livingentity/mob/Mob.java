package com.bourneless.roguelike.entity.livingentity.mob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.livingentity.LivingEntity;
import com.bourneless.roguelike.game.Instance;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Mob extends LivingEntity {

	protected int experienceWorth = 10;

	protected boolean showHitBar = false;
	protected MonsterStats stats;

	protected boolean hasDestination = false;
	protected Tile destinationTile;

	Timer hitBarTimer = new Timer(2000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			showHitBar = false;
		}
	});

	public Mob(Tile tile, BufferedImage image) {
		super(tile, image, EntityType.ENEMY);
		this.stats = new MonsterStats();
		this.image = Main.resourceLoader.monsterImages[stats.image];
	}

	public void update(int xOffset, int yOffset, Map map, Instance instance) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		if (!instance.getPlayerTurn()) {
			int getDestination = random.nextInt(2);
			if (getDestination == 1) {
				int randomTile = random.nextInt(4);
				Tile newTile;
				switch (randomTile) {
				case 0:
					if (tile.getTileY() - 1 > 0) {
						newTile = map.getTiles()[tile.getTileX()][tile
								.getTileY() - 1];
						if (newTile.isPassable()
								&& newTile.getEntities().isEmpty()) {
							this.layer = tile.getTileY();
							this.pos.y -= 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
						}
					}
					break;
				case 1:
					if (tile.getTileY() + 1 < map.getTiles().length) {
						newTile = map.getTiles()[tile.getTileX()][tile
								.getTileY() + 1];
						if (newTile.isPassable()
								&& newTile.getEntities().isEmpty()) {
							this.layer = tile.getTileY();
							this.pos.y += 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
						}
					}
					break;
				case 2:
					if (tile.getTileX() - 1 > 0) {
						newTile = map.getTiles()[tile.getTileX() - 1][tile
								.getTileY()];
						if (newTile.isPassable()
								&& newTile.getEntities().isEmpty()) {
							this.layer = tile.getTileY();
							this.pos.x -= 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
						}
					}
					break;
				case 3:
					if (tile.getTileX() + 1 < map.getTiles().length) {
						newTile = map.getTiles()[tile.getTileX() + 1][tile
								.getTileY()];
						if (newTile.isPassable()
								&& newTile.getEntities().isEmpty()) {
							this.layer = tile.getTileY();
							this.pos.x += 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
						}
					}
					break;
				default:
					break;
				}
			}
		}
	}

	public void paint(Graphics2D g) {
		if (!this.dead) {
			g.drawImage(image, pos.x + xOffset,
					pos.y + yOffset - image.getHeight() / 2, null);
		}

		if (showHitBar) {
			g.setColor(Color.black);
			g.fillRect(pos.x + image.getWidth() / 2 - 50 / 2 + xOffset, pos.y
					+ yOffset - image.getHeight() / 2, 50, 5);
			g.setColor(Color.red);
			g.fillRect(pos.x + image.getWidth() / 2 - 50 / 2 + xOffset, pos.y
					+ yOffset - image.getHeight() / 2, stats.health * 50
					/ stats.maxHealth, 5);
		}

		int stringLength = (int) g.getFontMetrics()
				.getStringBounds(stats.name, g).getWidth();
		int stringHeight = (int) g.getFontMetrics()
				.getStringBounds(stats.name, g).getHeight();
		int start = pos.x + xOffset + image.getWidth() / 2 - stringLength / 2;
		int xPos = pos.y + yOffset - image.getHeight() / 2 + stringHeight;
		g.setColor(Color.WHITE);
		g.drawString(stats.name, start, xPos);

	}

	public boolean getDead() {
		return this.dead;
	}

	public int getExperienceWorth() {
		return stats.experienceWorth;
	}

	public void hit(int health) {
		if (!dead) {
			showHitBar = true;
			if (!hitBarTimer.isRunning()) {
				hitBarTimer.start();
			} else {
				hitBarTimer.restart();
			}
			if (stats.health - health > 0) {
				stats.health -= health;
			} else {
				Main.resourceLoader
						.playClip(
								Main.resourceLoader.monsterDeath[random
										.nextInt(Main.resourceLoader.monsterDeath.length)],
								1f, false);
				stats.health = 0;
				this.dead = true;
				this.passable = true;
			}
		}
	}

}
