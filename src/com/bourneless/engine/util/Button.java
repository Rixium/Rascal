package com.bourneless.engine.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;

public class Button {

	private BufferedImage image;
	private Vector2 pos;
	private Vector2 mouseOffPos;
	private Vector2 mouseOverPos;
	private Rectangle rect;

	private boolean hoveredOff = true;

	public Button(BufferedImage image, Vector2 pos) {
		this.image = image;
		this.pos = pos;
		this.rect = new Rectangle(pos.x, pos.y, image.getWidth(),
				image.getHeight());

		this.mouseOverPos = new Vector2(pos.x, pos.y - 10);
		this.mouseOffPos = new Vector2(pos.x, pos.y);
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x, pos.y, null);
	}

	public Rectangle getRect() {
		return this.rect;
	}

	public void mouseOver() {
		this.pos = mouseOverPos;
		if (hoveredOff) {
			Main.resourceLoader.playClip(Main.resourceLoader.buttonHover, 1f,
					false);
			hoveredOff = false;
		}
	}

	public void mouseOff() {
		this.pos = mouseOffPos;
		hoveredOff = true;
	}

}
