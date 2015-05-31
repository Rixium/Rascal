package com.bourneless.roguelike.entity.player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.bourneless.engine.animation.Animation;
import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.FieldOfView;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.Tile;

public class Player extends Entity {

	private int lastKey;
	private boolean travelUp, travelDown, travelLeft, travelRight;
	private int playerXOff, playerYOff;

	private int walkSpeed = 4;

	private BufferedImage[] moveLeft = Main.resourceLoader.moveLeft;
	private BufferedImage[] moveRight = Main.resourceLoader.moveRight;
	private BufferedImage[] moveUp = Main.resourceLoader.moveUp;
	private BufferedImage[] moveDown = Main.resourceLoader.moveDown;

	private int animationSpeed = 400;
	private Animation moveLeftAnimation = new Animation(moveLeft, animationSpeed);
	private Animation moveRightAnimation = new Animation(moveRight, animationSpeed);
	private Animation moveUpAnimation = new Animation(moveUp, animationSpeed);
	private Animation moveDownAnimation = new Animation(moveDown, animationSpeed);

	public Player(Tile tile, BufferedImage image, int tileX, int tileY) {
		super(tile, image, tileX, tileY);
		type = EntityType.PLAYER;
	}

	public void paint(Graphics2D g) {		
		if(travelLeft)
			moveLeftAnimation.paint(g, new Vector2(pos.x + xOffset + playerXOff,
				pos.y - image.getHeight() / 2 + yOffset + playerYOff));
		
		else if(travelRight)
			moveRightAnimation.paint(g, new Vector2(pos.x + xOffset + playerXOff,
				pos.y - image.getHeight() / 2 + yOffset + playerYOff));
		
		else if(travelUp)
			moveUpAnimation.paint(g, new Vector2(pos.x + xOffset + playerXOff,
				pos.y - image.getHeight() / 2 + yOffset + playerYOff));
		
		else if(travelDown)
			moveDownAnimation.paint(g, new Vector2(pos.x + xOffset + playerXOff,
				pos.y - image.getHeight() / 2 + yOffset + playerYOff));
		
		else
			g.drawImage(image, pos.x + xOffset + playerXOff,
					pos.y - image.getHeight() / 2 + yOffset + playerYOff, null);
	}

	public void update(int xOffset, int yOffset, Map map) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		if (travelLeft) {
			if (pos.x + playerXOff > map.getRoom().getTiles()[(pos.x / 64) - 1][(pos.y / 64)]
					.getPos().x) {
				playerXOff -= walkSpeed;
			} else {
				travelLeft = false;
				moveLeftAnimation.stop();
				this.pos.x -= 64;
				this.tile = map.getRoom().getTiles()[tile.getTileX() - 1][tile.getTileY()];
				playerXOff = 0;
			}
		} else if (travelRight) {
			if (pos.x + playerXOff < map.getRoom().getTiles()[(pos.x / 64) + 1][(pos.y / 64)]
					.getPos().x) {
				playerXOff += walkSpeed;
			} else {
				travelRight = false;
				moveRightAnimation.stop();
				this.pos.x += 64;
				this.tile = map.getRoom().getTiles()[tile.getTileX() + 1][tile.getTileY()];
				playerXOff = 0;
			}
		} else if (travelUp) {
			if (pos.y + playerYOff > map.getRoom().getTiles()[(pos.x / 64)][(pos.y / 64) - 1]
					.getPos().y) {
				playerYOff -= walkSpeed;
			} else {
				travelUp = false;
				moveUpAnimation.stop();
				this.pos.y -= 64;
				this.tile = map.getRoom().getTiles()[tile.getTileX()][tile.getTileY() - 1];
				playerYOff = 0;
			}
		} else if (travelDown) {
			if (pos.y + playerYOff < map.getRoom().getTiles()[tile.getTileX()][tile.getTileY() + 1]
					.getPos().y) {
				playerYOff += walkSpeed;

			} else {
				travelDown = false;
				moveDownAnimation.stop();
				this.pos.y += 64;
				this.tile = map.getRoom().getTiles()[pos.x / 64][pos.y / 64];
				playerYOff = 0;
			}
		}
	}

	public void keyPressed(KeyEvent e, Map map) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 65 && lastKey != 65) {
			// A Key
			if (this.image.equals(Main.resourceLoader.player[3])) {
				if (map.getRoom().getTiles()[(pos.x / 64) - 1][(pos.y / 64)]
						.isPassable()) {
					travelLeft = true;
					
					if(moveLeftAnimation.isStopped()){
						moveLeftAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[3];
			}
			lastKey = 65;
		} else if (e.getKeyCode() == 68 && lastKey != 68) {
			// D Key
			if (this.image.equals(Main.resourceLoader.player[6])) {
				if (map.getRoom().getTiles()[(pos.x / 64) + 1][(pos.y / 64)]
						.isPassable()) {
					travelRight = true;
					
					if(moveRightAnimation.isStopped()){
						moveRightAnimation.start(false);
					}

				}
			} else {
				this.image = Main.resourceLoader.player[6];
			}
			lastKey = 68;
		}

		if (e.getKeyCode() == 87 && lastKey != 87) {
			// W Key
			if (this.image.equals(Main.resourceLoader.player[9])) {
				if (map.getRoom().getTiles()[(pos.x / 64)][(pos.y / 64) - 1]
						.isPassable()) {
					travelUp = true;
					
					if(moveUpAnimation.isStopped()){
						moveUpAnimation.start(false);
					}
					
				}
			} else {
				this.image = Main.resourceLoader.player[9];
			}
			lastKey = 87;
		} else if (e.getKeyCode() == 83 && lastKey != 83) {
			// S Key
			if (this.image.equals(Main.resourceLoader.player[0])) {
				if (map.getRoom().getTiles()[(pos.x / 64)][(pos.y / 64) + 1]
						.isPassable()) {
					travelDown = true;
					
					if(moveDownAnimation.isStopped()){
						moveDownAnimation.start(false);
					}
					
				}
			} else {
				this.image = Main.resourceLoader.player[0];
			}
			lastKey = 83;
		}
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

}
