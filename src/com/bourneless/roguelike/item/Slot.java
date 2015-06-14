package com.bourneless.roguelike.item;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.bourneless.engine.math.Vector2;

public class Slot {

	private Item item;
	private Vector2 pos;
	private int slotSize = 0;
	private boolean free = true;

	private Rectangle rect;
	private boolean popup;

	public Slot(Vector2 pos, int slotSize) {
		this.pos = pos;
		this.slotSize = slotSize;

		rect = new Rectangle(pos.x, pos.y, slotSize, slotSize);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawRect(pos.x, pos.y, slotSize, slotSize);
		if (item != null) {
			g.drawImage(item.getItemInvImage(), pos.x, pos.y, null);

			if (popup) {
				g.setFont(new Font("Arial", Font.PLAIN, 15));
				String itemString = "";

				if (item.getStats().itemType != ItemType.FOOD) {
					itemString = item.getStats().itemName + " of "
							+ item.getStats().speciality + " | " + "Strength: "
							+ item.getStats().itemStrength + " | "
							+ "Defence: " + item.getStats().itemFortitude
							+ " | " + item.getDegradation();
				} else {
					itemString = item.getStats().itemName + " | " + "Heal: "
							+ item.getStats().itemHealPower + " | "
							+ item.getDegradation();
				}

				int stringLength = (int) g.getFontMetrics()
						.getStringBounds(itemString, g).getWidth();
				int stringHeight = (int) g.getFontMetrics()
						.getStringBounds(itemString, g).getHeight();
				int start = pos.x;
				int xPos = pos.y - stringHeight - 10;

				g.setColor(Color.black);
				g.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.7f));
				g.fillRect(start - 10, xPos - stringHeight - 10,
						stringLength + 20, stringHeight + 20);
				g.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 1f));

				if (item != null) {
					if (item.getStats().rarity <= 5) {
						g.setColor(Color.white);
					} else if (item.getStats().rarity > 5
							&& item.getStats().rarity <= 6) {
						g.setColor(Color.green);
					} else if (item.getStats().rarity > 6
							&& item.getStats().rarity <= 7) {
						g.setColor(Color.yellow);
					} else if (item.getStats().rarity == 8) {
						g.setColor(new Color(9502975));
					}
				}

				g.drawString(itemString, start, xPos);
			}
		}
	}

	public void setItem(Item item) {
		this.item = item;
		free = false;
	}

	public void removeItem() {
		this.item = null;
		popup = false;
		free = true;
	}

	public boolean isFree() {
		return this.free;
	}

	public Item getItem() {
		return this.item;
	}

	public Rectangle getRect() {
		return this.rect;
	}

	public void showPopup() {
		popup = true;
	}

	public void hidePopup() {
		popup = false;
	}
}
