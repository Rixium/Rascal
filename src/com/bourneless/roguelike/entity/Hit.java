package com.bourneless.roguelike.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;

public class Hit {

	private int hit;
	private Vector2 pos;
	private boolean dead = false;
	private int speed = 2;
	private int xOffset, yOffset;

	Timer showHitTimer = new Timer(400, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			dead = true;
		}
	});

	public Hit(int hit, Vector2 pos) {
		this.hit = hit;
		this.pos = pos;

		showHitTimer.start();
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		pos.y -= speed;
	}

	public void paint(Graphics2D g) {
		g.setFont(Main.resourceLoader.gameFont);
		g.setColor(Color.white);
		g.drawString(hit + "", pos.x + xOffset, pos.y + yOffset);
	}

	public boolean getDead() {
		return this.dead;
	}

}
