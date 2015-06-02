package com.bourneless.roguelike.entity.livingentity.player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.bourneless.engine.animation.Animation;
import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.FieldOfView;
import com.bourneless.roguelike.entity.destroyableentity.DestroyableEntity;
import com.bourneless.roguelike.entity.livingentity.LivingEntity;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Player extends LivingEntity {

	private int lastKey;
	private boolean travelUp, travelDown, travelLeft, travelRight;
	private int playerXOff, playerYOff;

	private int walkSpeed = 4;
	private int viewDistance = 5;

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

	private Rectangle rect;

	private Stats stats;

	public Player(Tile tile, BufferedImage image) {
		super(tile, image, EntityType.PLAYER);
		type = EntityType.PLAYER;
		rect = new Rectangle(tile.getPos().x, tile.getPos().y,
				image.getWidth(), image.getHeight());
		stats = new Stats();
	}

	public void paint(Graphics2D g) {
		if (travelLeft)
			moveLeftAnimation.paint(g, new Vector2(
					pos.x + xOffset + playerXOff, pos.y - image.getHeight() / 2
							+ yOffset + playerYOff));

		else if (travelRight)
			moveRightAnimation.paint(g, new Vector2(pos.x + xOffset
					+ playerXOff, pos.y - image.getHeight() / 2 + yOffset
					+ playerYOff));

		else if (travelUp)
			moveUpAnimation.paint(g, new Vector2(pos.x + xOffset + playerXOff,
					pos.y - image.getHeight() / 2 + yOffset + playerYOff));

		else if (travelDown)
			moveDownAnimation.paint(g, new Vector2(
					pos.x + xOffset + playerXOff, pos.y - image.getHeight() / 2
							+ yOffset + playerYOff));

		else
			g.drawImage(image, pos.x + xOffset + playerXOff,
					pos.y - image.getHeight() / 2 + yOffset + playerYOff, null);
	}

	public void update(int xOffset, int yOffset, Map map) {
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

				playerXOff = 0;
			}
		} else if (travelRight) {
			if (pos.x + playerXOff < map.getTiles()[tile.getTileX() + 1][tile
					.getTileY()].getPos().x) {
				playerXOff += walkSpeed;
			} else {
				travelRight = false;
				moveRightAnimation.stop();
				this.pos.x += 64;

				Tile newTile = map.getTiles()[tile.getTileX() + 1][tile
						.getTileY()];
				newTile.addEntity(this);
				tile.removeEntity(this);
				tile = newTile;

				playerXOff = 0;
			}
		} else if (travelUp) {
			if (pos.y + playerYOff > map.getTiles()[tile.getTileX()][tile
					.getTileY() - 1].getPos().y) {
				playerYOff -= walkSpeed;
			} else {
				travelUp = false;
				moveUpAnimation.stop();
				this.pos.y -= 64;
				Tile newTile = map.getTiles()[tile.getTileX()][tile
						.getTileY() - 1];
				newTile.addEntity(this);
				tile.removeEntity(this);
				tile = newTile;

				playerYOff = 0;
			}
		} else if (travelDown) {

			if (pos.y + playerYOff < map.getTiles()[tile.getTileX()][tile
					.getTileY() + 1].getPos().y) {
				playerYOff += walkSpeed;

			} else {
				travelDown = false;
				moveDownAnimation.stop();
				this.pos.y += 64;

				Tile newTile = map.getTiles()[tile.getTileX()][tile
						.getTileY() + 1];
				newTile.addEntity(this);
				tile.removeEntity(this);
				tile = newTile;

				playerYOff = 0;
			}
		}
	}

	public void keyPressed(KeyEvent e, Map map) {
		if (e.getKeyCode() == 65 && lastKey != 65) {
			// A Key
			if (this.image.equals(Main.resourceLoader.player[4])) {
				if (map.getTiles()[tile.getTileX() - 1][tile.getTileY()]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX() - 1][tile.getTileY()].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX() - 1][tile.getTileY()]
								.getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.strength);
						} else {
							travelLeft = true;
							if (moveLeftAnimation.isStopped()) {
								Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
								moveLeftAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX() - 1][tile.getTileY()]
						.isPassable()) {
					travelLeft = true;

					if (moveLeftAnimation.isStopped()) {
						Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
						moveLeftAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[4];
			}
		} else if (e.getKeyCode() == 68 && lastKey != 68) {
			// D Key
			if (this.image.equals(Main.resourceLoader.player[7])) {
				
				if (map.getTiles()[tile.getTileX() + 1][tile.getTileY()]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX() + 1][tile.getTileY()].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX() + 1][tile.getTileY()]
								.getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.strength);
						} else {
							travelRight = true;
							if (moveRightAnimation.isStopped()) {
								Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
								moveRightAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX() + 1][tile.getTileY()]
						.isPassable()) {
					travelRight = true;
					if (moveRightAnimation.isStopped()) {
						Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
						moveRightAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[7];
			}
		}

		if (e.getKeyCode() == 87 && lastKey != 87) {
			// W Key
			if (this.image.equals(Main.resourceLoader.player[9])) {
				if (map.getTiles()[tile.getTileX()][tile.getTileY() - 1]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX()][tile.getTileY()  - 1].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX()][tile.getTileY()  - 1]
								.getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE && !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.strength);
						} else {
							travelUp = true;
							if (moveUpAnimation.isStopped()) {
								Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
								moveUpAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX()][tile.getTileY() - 1]
						.isPassable()) {
					travelUp = true;

					if (moveUpAnimation.isStopped()) {
						Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
						moveUpAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[9];
			}
		} else if (e.getKeyCode() == 83 && lastKey != 83) {
			// S Key
			if (this.image.equals(Main.resourceLoader.player[0])) {
				if (map.getTiles()[tile.getTileX()][tile.getTileY() + 1]
						.hasEntity()) {
					for (int i = 0; i < map.getTiles()[tile.getTileX()][tile.getTileY() + 1].getEntities().size(); i++) {
						Entity entity = map.getTiles()[tile.getTileX()][tile.getTileY() + 1]
								.getEntities().get(i);
						if (entity.getType() == EntityType.BREAKABLE && !entity.getPassable()) {
							DestroyableEntity dEnt = (DestroyableEntity) entity;
							dEnt.hit(stats.strength);
						} else {
							travelDown = true;
							if (moveDownAnimation.isStopped()) {
								Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
								moveDownAnimation.start(false);
							}
						}
					}
				} else if (map.getTiles()[tile.getTileX()][tile.getTileY() + 1]
						.isPassable()) {
					travelDown = true;
					if (moveDownAnimation.isStopped()) {
						Main.resourceLoader.playClip(Main.resourceLoader.walkSounds[random.nextInt(Main.resourceLoader.walkSounds.length)], .5f, false);
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

}
