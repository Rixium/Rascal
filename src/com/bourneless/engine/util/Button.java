package com.bourneless.engine.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
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
	private boolean popup = false;

	private String popupText;

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

		if (popup) {

			g.setFont(new Font("Arial", Font.PLAIN, 15));

			int stringLength = (int) g.getFontMetrics()
					.getStringBounds(popupText, g).getWidth();
			int stringHeight = (int) g.getFontMetrics()
					.getStringBounds(popupText, g).getHeight();
			int start = pos.x;
			int xPos = pos.y - stringHeight - 10;

			g.setColor(Color.black);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.7f));
			g.fillRect(start - 10, xPos - stringHeight - 10, stringLength + 20,
					stringHeight + 20);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1f));
			g.setColor(Color.white);
			g.drawString(popupText, start, xPos);
		}
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

	public void showPopup() {
		popup = true;
	}

	public void hidePopup() {
		popup = false;
	}

	public void setPopupText(String popupText) {
		this.popupText = popupText;
	}

}
