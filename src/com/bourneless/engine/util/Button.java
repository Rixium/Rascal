package com.bourneless.engine.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bourneless.engine.math.Vector2;

public class Button {

	private BufferedImage image;
	private Vector2 pos;
	private Rectangle rect;

	public Button(BufferedImage image, Vector2 pos) {
		this.image = image;
		this.pos = pos;
		this.rect = new Rectangle(pos.x, pos.y, image.getWidth(),
				image.getHeight());
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x, pos.y, null);
	}

	public Rectangle getRect() {
		return this.rect;
	}

}
