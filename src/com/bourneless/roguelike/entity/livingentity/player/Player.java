package com.bourneless.roguelike.entity.livingentity.player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.bourneless.engine.animation.Animation;
import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.FieldOfView;
import com.bourneless.roguelike.entity.Hit;
import com.bourneless.roguelike.entity.destroyableentity.DestroyableEntity;
import com.bourneless.roguelike.entity.livingentity.LivingEntity;
import com.bourneless.roguelike.entity.livingentity.mob.Mob;
import com.bourneless.roguelike.game.Instance;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Player extends LivingEntity {

	private int lastKey;
	private boolean travelUp, travelDown, travelLeft, travelRight;
	private int playerXOff, playerYOff;

	private int walkSpeed = 4;
	private int viewDistance = 6;

	private BufferedImage[] moveLeft = Main.resourceLoader.moveLeft;
	private BufferedImage[] moveRight = Main.resourceLoader.moveRight;
	private BufferedImage[] moveUp = Main.resourceLoader.moveUp;
	private BufferedImage[] moveDown = Main.resourceLoader.moveDown;

	private int animationSpeed = 100;
	private Animation moveLeftAnimation = new Animation(moveLeft,
			animationSpeed);
	private Animation moveRightAnimation = new Animation(moveRight,
			animationSpeed);
	private Animation moveUpAnimation = new Animation(moveUp, animationSpeed);
	private Animation moveDownAnimation = new Animation(moveDown,
			animationSpeed);

	private FieldOfView fOV = new FieldOfView();
	private Random random = new Random();
	private Instance instance;

	private Stats stats;
	private Vector2 hitPos;
	private int hit;
	
	private Inventory  inventory;

	private ArrayList<Hit> hits = new ArrayList<Hit>();

	public Player(Tile tile, BufferedImage image) {
		super(tile, image, EntityType.PLAYER);
		type = EntityType.PLAYER;
		stats = new Stats(this);
		inventory = new Inventory();
	}

	public void paint(Graphics2D g) {
		if (travelLeft) {
			moveLeftAnimation.paint(g, new Vector2(
					pos.x + xOffset + playerXOff, pos.y - image.getHeight() / 4
							- Tile.size + yOffset + playerYOff));
		} else if (travelRight) {
			moveRightAnimation.paint(g, new Vector2(pos.x + xOffset
					+ playerXOff, pos.y - image.getHeight() / 4 - Tile.size
					+ yOffset + playerYOff));
		} else if (travelUp) {
			moveUpAnimation.paint(g, new Vector2(pos.x + xOffset + playerXOff,
					pos.y - image.getHeight() / 4 - Tile.size + yOffset
							+ playerYOff));
		} else if (travelDown) {
			moveDownAnimation.paint(g, new Vector2(
					pos.x + xOffset + playerXOff, pos.y - image.getHeight() / 4
							- Tile.size + yOffset + playerYOff));
		} else {
			g.drawImage(image, pos.x + xOffset + playerXOff,
					pos.y - image.getHeight() / 4 - Tile.size + yOffset
							+ playerYOff, null);
		}

	}

	public void update(int xOffset, int yOffset, Map map, Instance instance) {
		this.instance = instance;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		fOV.CheckFieldOfView(map, this);
		if (travelLeft) {
			if (pos.x + playerXOff > map.getTiles()[tile.getTileX() - 1][tile
					.getTileY()].getPos().x) {
				playerXOff -= walkSpeed;
			} else {
				travelLeft = false;
				moveLeftAnimation.stop();
				this.pos.x -= 64;
				Tile newTile = map.getTiles()[tile.getTileX() - 1][tile
						.getTileY()];
				newTile.addEntity(this);
				tile.removeEntity(this);
				tile = newTile;
				this.layer = tile.getTileY();
				playerXOff = 0;
				instance.setPlayerTurn(false);
			}
		} else if (travelRight) {
			if (pos.x + playerXOff < map.getTiles()[tile.getTileX() + 1][tile
					.getTileY()].getPos().x) {
				playerXOff += walkSpeed;
			} else {
				travelRight = false;
				moveRightAnimation.stop();
				this.pos.x += 64;
				this.layer = tile.getTileY();
				Tile newTile = map.getTiles()[tile.getTileX() + 1][tile
						.getTileY()];
				newTile.addEntity(this);
				tile.removeEntity(this);
				tile = newTile;
				playerXOff = 0;
				instance.setPlayerTurn(false);
			}
		} else if (travelUp) {
			if (pos.y + playerYOff > map.getTiles()[tile.getTileX()][tile
					.getTileY() - 1].getPos().y) {
				playerYOff -= walkSpeed;
			} else {
				travelUp = false;
				moveUpAnimation.stop();
				this.pos.y -= 64;
				Tile newTile = map.getTiles()[tile.getTileX()][tile.getTileY() - 1];
				newTile.addEntity(this);
				this.layer = tile.getTileY();
				tile.removeEntity(this);
				tile = newTile;
				playerYOff = 0;
				instance.setPlayerTurn(false);
			}
		} else if (travelDown) {

			if (pos.y + playerYOff < map.getTiles()[tile.getTileX()][tile
					.getTileY() + 1].getPos().y) {
				playerYOff += walkSpeed;

			} else {
				travelDown = false;
				moveDownAnimation.stop();
				this.pos.y += Tile.size;
				this.layer = tile.getTileY();
				Tile newTile = map.getTiles()[tile.getTileX()][tile.getTileY() + 1];
				newTile.addEntity(this);
				tile.removeEntity(this);
				tile = newTile;
				playerYOff = 0;
				instance.setPlayerTurn(false);
			}
		}
	}

	public void keyPressed(KeyEvent e, Map map) {
		if (e.getKeyCode() == 65 && lastKey != 65 || e.getKeyCode() == 37
				&& lastKey != 37) {
			// A Key
			if (this.image.equals(Main.resourceLoader.player[4])) {
				if (map.getTiles()[tile.getTileX() - 1][tile.getTileY()]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX() - 1][tile
							.getTileY()].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX() - 1][tile
								.getTileY()].getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else if (entity.getType() == EntityType.ENEMY
								&& !entity.getPassable()) {
							Mob mob = (Mob) entity;
							Main.resourceLoader
									.playClip(
											Main.resourceLoader.hitSounds[random
													.nextInt(Main.resourceLoader.hitSounds.length)],
											1f, false);
							mob.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else {
							travelLeft = true;
							if (moveLeftAnimation.isStopped()) {
								Main.resourceLoader
										.playClip(
												Main.resourceLoader.walkSounds[random
														.nextInt(Main.resourceLoader.walkSounds.length)],
												.5f, false);
								moveLeftAnimation.start(false);
								instance.setPlayerTurn(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX() - 1][tile.getTileY()]
						.isPassable()) {
					travelLeft = true;

					if (moveLeftAnimation.isStopped()) {
						Main.resourceLoader
								.playClip(
										Main.resourceLoader.walkSounds[random
												.nextInt(Main.resourceLoader.walkSounds.length)],
										.5f, false);
						moveLeftAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[4];
			}
		} else if (e.getKeyCode() == 68 && lastKey != 68
				|| e.getKeyCode() == 39 && lastKey != 39) {
			// D Key
			if (this.image.equals(Main.resourceLoader.player[7])) {

				if (map.getTiles()[tile.getTileX() + 1][tile.getTileY()]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX() + 1][tile
							.getTileY()].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX() + 1][tile
								.getTileY()].getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else if (entity.getType() == EntityType.ENEMY
								&& !entity.getPassable()) {
							Mob mob = (Mob) entity;
							Main.resourceLoader
									.playClip(
											Main.resourceLoader.hitSounds[random
													.nextInt(Main.resourceLoader.hitSounds.length)],
											1f, false);
							mob.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else {
							travelRight = true;
							if (moveRightAnimation.isStopped()) {
								Main.resourceLoader
										.playClip(
												Main.resourceLoader.walkSounds[random
														.nextInt(Main.resourceLoader.walkSounds.length)],
												.5f, false);
								moveRightAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX() + 1][tile.getTileY()]
						.isPassable()) {
					travelRight = true;
					if (moveRightAnimation.isStopped()) {
						Main.resourceLoader
								.playClip(
										Main.resourceLoader.walkSounds[random
												.nextInt(Main.resourceLoader.walkSounds.length)],
										.5f, false);
						moveRightAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[7];
			}
		}

		if (e.getKeyCode() == 87 && lastKey != 87 || e.getKeyCode() == 38
				&& lastKey != 38) {
			// W Key
			if (this.image.equals(Main.resourceLoader.player[9])) {
				if (map.getTiles()[tile.getTileX()][tile.getTileY() - 1]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX()][tile
							.getTileY() - 1].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX()][tile
								.getTileY() - 1].getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else if (entity.getType() == EntityType.ENEMY
								&& !entity.getPassable()) {
							Main.resourceLoader
									.playClip(
											Main.resourceLoader.hitSounds[random
													.nextInt(Main.resourceLoader.hitSounds.length)],
											1f, false);
							Mob mob = (Mob) entity;
							mob.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else {

							travelUp = true;
							if (moveUpAnimation.isStopped()) {
								Main.resourceLoader
										.playClip(
												Main.resourceLoader.walkSounds[random
														.nextInt(Main.resourceLoader.walkSounds.length)],
												.5f, false);
								moveUpAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX()][tile.getTileY() - 1]
						.isPassable()) {
					travelUp = true;

					if (moveUpAnimation.isStopped()) {
						Main.resourceLoader
								.playClip(
										Main.resourceLoader.walkSounds[random
												.nextInt(Main.resourceLoader.walkSounds.length)],
										.5f, false);
						moveUpAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[9];
			}
		} else if (e.getKeyCode() == 83 && lastKey != 83
				|| e.getKeyCode() == 40 && lastKey != 40) {
			// S Key
			if (this.image.equals(Main.resourceLoader.player[0])) {
				if (map.getTiles()[tile.getTileX()][tile.getTileY() + 1]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX()][tile
							.getTileY() + 1].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX()][tile
								.getTileY() + 1].getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else if (entity.getType() == EntityType.ENEMY
								&& !entity.getPassable()) {
							Main.resourceLoader
									.playClip(
											Main.resourceLoader.hitSounds[random
													.nextInt(Main.resourceLoader.hitSounds.length)],
											1f, false);
							Mob mob = (Mob) entity;
							mob.hit(stats.getStrength());
							instance.setPlayerTurn(false);
						} else {
							travelDown = true;
							if (moveDownAnimation.isStopped()) {
								Main.resourceLoader
										.playClip(
												Main.resourceLoader.walkSounds[random
														.nextInt(Main.resourceLoader.walkSounds.length)],
												.5f, false);
								moveDownAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX()][tile.getTileY() + 1]
						.isPassable()) {
					travelDown = true;
					if (moveDownAnimation.isStopped()) {
						Main.resourceLoader
								.playClip(
										Main.resourceLoader.walkSounds[random
												.nextInt(Main.resourceLoader.walkSounds.length)],
										.5f, false);
						moveDownAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[0];
			}
		} else if (e.getKeyCode() == 69) {

			// E Key
		}

		lastKey = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) {
		lastKey = 0;
	}

	public int getYOff() {
		return this.playerYOff;
	}

	public int getXOff() {
		return this.playerXOff;
	}

	public int getViewDistance() {
		return this.viewDistance;
	}

	public Rectangle getRect() {
		return rect;
	}

	public Stats getStats() {
		return this.stats;
	}

	public void hit(Mob mob) {

		hit = mob.getStats().strength * random.nextInt(mob.getStats().reflexes)
				- random.nextInt(stats.fortitude) * random.nextInt(stats.luck);

		while (hit < 0) {
			hit = mob.getStats().strength
					* random.nextInt(mob.getStats().reflexes)
					- random.nextInt(stats.fortitude)
					* random.nextInt(stats.luck);
		}

		instance.getHits().add(
				new Hit(hit, new Vector2(pos.x + image.getWidth() / 2, pos.y
						- image.getHeight() / 4)));

		System.out.println(mob.getStats().name + " hits for " + hit + "!");

		if (hit > 0) {
			if (hit > stats.fortitude) {
				if (!Main.resourceLoader.playerHurt[0].isActive()
						&& !Main.resourceLoader.playerHurt[1].isActive()
						&& !Main.resourceLoader.playerHurt[2].isActive()) {
					int play = random.nextInt(2);
					if (play == 0) {
						Main.resourceLoader
								.playClip(
										Main.resourceLoader.playerHurt[random
												.nextInt(Main.resourceLoader.playerHurt.length)],
										1f, false);
					}
				}
			}
			this.health -= hit;
		}

		if (this.health <= 0) {
			Main.resourceLoader.playClip(Main.resourceLoader.playerDeath[random
					.nextInt(Main.resourceLoader.playerDeath.length)], 1f,
					false);
			Main.game.getGameStats().totalDeaths++;
			this.dead = true;
			moveLeftAnimation.stop();
			moveRightAnimation.stop();
			moveUpAnimation.stop();
			moveDownAnimation.stop();

			instance.showDeathScreen();
		}
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
}
