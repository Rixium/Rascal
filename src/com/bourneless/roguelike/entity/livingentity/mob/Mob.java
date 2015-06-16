package com.bourneless.roguelike.entity.livingentity.mob;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.Hit;
import com.bourneless.roguelike.entity.livingentity.LivingEntity;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.game.Instance;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Mob extends LivingEntity {

	protected int experienceWorth = 10;

	protected boolean showHitBar = false;
	protected MonsterStats stats;

	protected boolean hasDestination = false;
	protected Tile destinationTile;

	protected int viewDistance = 5;

	protected MonsterFOV fov = new MonsterFOV();

	Timer hitBarTimer = new Timer(2000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			showHitBar = false;
		}
	});

	protected boolean showAlert = false;

	Timer alertTimer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			showAlert = false;
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

		turn: if (!instance.getPlayerTurn()) {

			fov.CheckFieldOfView(map, map.getPlayer(), this);

			boolean hasPlayer = false;

			if (map.getTiles()[tile.getTileX() - 1][tile.getTileY()]
					.hasEntity()) {
				for (Entity entity : map.getTiles()[tile.getTileX() - 1][tile
						.getTileY()].getEntities()) {
					if (entity.getType() == EntityType.PLAYER) {
						Player player = (Player) entity;
						player.hit(this);
						break turn;
					}
				}
			}
			if (map.getTiles()[tile.getTileX() + 1][tile.getTileY()]
					.hasEntity()) {
				for (Entity entity : map.getTiles()[tile.getTileX() + 1][tile
						.getTileY()].getEntities()) {
					if (entity.getType() == EntityType.PLAYER) {
						Player player = (Player) entity;
						player.hit(this);
						break turn;
					}
				}
			}
			if (map.getTiles()[tile.getTileX()][tile.getTileY() - 1]
					.hasEntity()) {
				for (Entity entity : map.getTiles()[tile.getTileX()][tile
						.getTileY() - 1].getEntities()) {
					if (entity.getType() == EntityType.PLAYER) {
						Player player = (Player) entity;
						player.hit(this);
						break turn;
					}
				}
			}
			if (map.getTiles()[tile.getTileX()][tile.getTileY() + 1]
					.hasEntity()) {
				for (Entity entity : map.getTiles()[tile.getTileX()][tile
						.getTileY() + 1].getEntities()) {
					if (entity.getType() == EntityType.PLAYER) {
						Player player = (Player) entity;
						player.hit(this);
						break turn;
					}
				}
			}

			if (destinationTile == null) {
				int getDestination = random.nextInt(2);
				if (getDestination == 1 && !hasPlayer) {
					int randomTile = random.nextInt(4);
					Tile newTile;
					switch (randomTile) {
					case 0:
						if (tile.getTileY() - 1 > 0) {
							newTile = map.getTiles()[tile.getTileX()][tile
									.getTileY() - 1];
							if (newTile.isPassable()) {
								this.layer = tile.getTileY();
								this.pos.y -= 64;
								newTile.addEntity(this);
								tile.removeEntity(this);
								tile = newTile;
								break turn;
							}
						}
						break;
					case 1:
						if (tile.getTileY() + 1 < map.getTiles().length) {
							newTile = map.getTiles()[tile.getTileX()][tile
									.getTileY() + 1];
							if (newTile.isPassable()) {
								this.layer = tile.getTileY();
								this.pos.y += 64;
								newTile.addEntity(this);
								tile.removeEntity(this);
								tile = newTile;
								break turn;
							}
						}
						break;
					case 2:
						if (tile.getTileX() - 1 > 0) {
							newTile = map.getTiles()[tile.getTileX() - 1][tile
									.getTileY()];
							if (newTile.isPassable()) {
								this.layer = tile.getTileY();
								this.pos.x -= 64;
								newTile.addEntity(this);
								tile.removeEntity(this);
								tile = newTile;
								break turn;
							}
						}
						break;
					case 3:
						if (tile.getTileX() + 1 < map.getTiles().length) {
							newTile = map.getTiles()[tile.getTileX() + 1][tile
									.getTileY()];
							if (newTile.isPassable()) {
								this.layer = newTile.getTileY();
								this.pos.x += 64;
								newTile.addEntity(this);
								tile.removeEntity(this);
								tile = newTile;
								break turn;
							}
						}
						break;
					default:
						break;
					}
				}
			} else {

				int randomDir = random.nextInt(4);

				if (randomDir == 0) {
					if (destinationTile.getTileX() < tile.getTileX()) {
						Tile newTile = map.getTiles()[tile.getTileX() - 1][tile
								.getTileY()];
						if (newTile.isPassable()) {
							this.layer = newTile.getTileY();
							this.pos.x -= 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
							tile.setPassable(false);
						}
					}
				} else if (randomDir == 1) {
					if (destinationTile.getTileX() > tile.getTileX()) {
						Tile newTile = map.getTiles()[tile.getTileX() + 1][tile
								.getTileY()];
						if (newTile.isPassable()) {
							this.layer = newTile.getTileY();
							this.pos.x += 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
							tile.setPassable(false);
						}
					}
				} else if (randomDir == 2) {
					if (destinationTile.getTileY() > tile.getTileY()) {
						Tile newTile = map.getTiles()[tile.getTileX()][tile
								.getTileY() + 1];
						if (newTile.isPassable()) {
							this.layer = newTile.getTileY();
							this.pos.y += 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
							tile.setPassable(false);
						}
					}
				} else if (randomDir == 3) {
					if (destinationTile.getTileY() < tile.getTileY()) {
						Tile newTile = map.getTiles()[tile.getTileX()][tile
								.getTileY() - 1];
						if (newTile.isPassable()) {
							this.layer = newTile.getTileY();
							this.pos.y -= 64;
							newTile.addEntity(this);
							tile.removeEntity(this);
							tile = newTile;
							tile.setPassable(false);
						}
					}
				} else {
					destinationTile = null;
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

		if (showAlert) {
			g.drawImage(Main.resourceLoader.exclamation,
					pos.x + xOffset + image.getWidth() / 2
							- Main.resourceLoader.exclamation.getWidth() / 4,
					pos.y + yOffset - image.getHeight() / 2
							- Main.resourceLoader.exclamation.getHeight() / 2,
					Main.resourceLoader.exclamation.getWidth() / 2,
					Main.resourceLoader.exclamation.getHeight() / 2, null);
		}
	}

	public boolean getDead() {
		return this.dead;
	}

	public int getExperienceWorth() {
		return stats.experienceWorth;
	}

	public void kill() {
		this.dead = true;
		this.passable = true;
	}

	public void hit(int health, Instance instance, Player player) {
		if (!dead) {

			int hit = player.getStats().strength
					* random.nextInt(player.getStats().reflexes)
					- random.nextInt(stats.fortitude)
					* random.nextInt(stats.luck);

			if (hit < 0) {
				hit = 0;
			}

			showHitBar = true;
			if (!hitBarTimer.isRunning()) {
				hitBarTimer.start();
			} else {
				hitBarTimer.restart();
			}
			if (stats.health - health > 0) {
				instance.getHits().add(
						new Hit(hit, new Vector2(pos.x + image.getWidth() / 2,
								pos.y - image.getHeight() / 4)));
				stats.health -= hit;
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

	public MonsterStats getStats() {
		return this.stats;
	}

	public int getViewDistance() {
		return this.viewDistance;
	}

	public void setDestination(Tile tile) {
		if (destinationTile == null) {
			showAlert = true;
			alertTimer.start();
		}

		this.destinationTile = tile;

	}

}
