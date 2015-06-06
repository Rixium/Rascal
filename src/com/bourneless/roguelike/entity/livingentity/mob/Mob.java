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
import com.bourneless.roguelike.map.tile.Tile;

public class Mob extends LivingEntity {

	protected int experience = 10;

	protected boolean showHitBar = false;

	Timer hitBarTimer = new Timer(2000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			showHitBar = false;
		}
	});

	public Mob(Tile tile, BufferedImage image) {
		super(tile, image, EntityType.ENEMY);
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g) {
		if (!this.dead) {
			g.drawImage(image, pos.x + xOffset, pos.y + yOffset, null);
		}

		if (showHitBar) {
			g.setColor(Color.black);
			g.fillRect(pos.x + image.getWidth() / 2 - 50 / 2 + xOffset, pos.y
					+ yOffset - image.getHeight() / 2, 50, 5);
			g.setColor(Color.red);
			g.fillRect(pos.x + image.getWidth() / 2 - 50 / 2 + xOffset, pos.y
					+ yOffset - image.getHeight() / 2, this.health * 50 / 100,
					5);
		}

	}

	public boolean getDead() {
		return this.dead;
	}

	public int getExperience() {
		return this.experience;
	}

	public void hit(int health) {
		if (!dead) {
			showHitBar = true;
			if (!hitBarTimer.isRunning()) {
				hitBarTimer.start();
			} else {
				hitBarTimer.restart();
			}
			if (this.health - health > 0) {
				this.health -= health;
			} else {
				Main.resourceLoader
						.playClip(
								Main.resourceLoader.monsterDeath[random
										.nextInt(Main.resourceLoader.monsterDeath.length)],
								1f, false);
				this.health = 0;
				this.dead = true;
				this.passable = true;
			}
		}
	}

}
