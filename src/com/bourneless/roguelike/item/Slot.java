package com.bourneless.roguelike.item;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.screen.GameScreen;

public class Slot {

	private Item item;
	private Vector2 pos;
	private int slotSize = 0;
	private boolean free = true;

	private int statBoxWidth = 199;
	private int statBoxHeight = 100;
	private Vector2 statBox = new Vector2(Main.GAME_WIDTH - statBoxWidth - 10,
			Main.GAME_HEIGHT - statBoxHeight - 10);
	private Color statBoxColour = new Color(14796090);

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

			g.drawRect(pos.x + 1, pos.y + 1, slotSize - 2, slotSize - 2);

			g.drawImage(item.getItemInvImage(), pos.x, pos.y, null);

			if (popup) {
				g.setFont(new Font("Arial", Font.PLAIN, 15));

				g.setColor(Color.black);
				g.fillRect(statBox.x, statBox.y, statBoxWidth, statBoxHeight);
				g.setColor(statBoxColour);
				g.drawRect(statBox.x, statBox.y, statBoxWidth, statBoxHeight);
				g.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 1f));

				if (item != null) {
					if (item.getStats().rarity <= 5) {
						g.setColor(Color.white);
						g.drawImage(Main.resourceLoader.notRare, statBox.x,
								statBox.y - 5, null);
					} else if (item.getStats().rarity > 5
							&& item.getStats().rarity <= 6) {
						g.setColor(Color.green);
						g.drawImage(Main.resourceLoader.rare, statBox.x,
								statBox.y - 5, null);
					} else if (item.getStats().rarity > 6
							&& item.getStats().rarity <= 7) {
						g.setColor(Color.yellow);
						g.drawImage(Main.resourceLoader.elite, statBox.x,
								statBox.y - 5, null);
					} else if (item.getStats().rarity == 8) {
						g.setColor(new Color(9502975));
						g.drawImage(Main.resourceLoader.epic, statBox.x,
								statBox.y - 5, null);
					}
				}
				if (item != null) {
					if (item.getStats().itemType != ItemType.FOOD) {

						g.drawString(
								item.getStats().prefix
										+ item.getStats().itemName + " of "
										+ item.getStats().speciality,
								statBox.x + 10, statBox.y + 20);

						g.setColor(Color.WHITE);
						g.drawString("Strength: "
								+ item.getStats().itemStrength, statBox.x + 10,
								statBox.y + 35);
						g.drawString("Defence: "
								+ item.getStats().itemFortitude,
								statBox.x + 10, statBox.y + 50);
						g.drawString("Reflexes: "
								+ item.getStats().itemReflexes, statBox.x + 10,
								statBox.y + 65);
						g.drawString("Constitution: "
								+ item.getStats().itemConstitution,
								statBox.x + 10, statBox.y + 80);
						g.drawString("Level Requirement: "
								+ item.getStats().level, statBox.x + 10,
								statBox.y + 95);
					} else {
						g.drawString(
								item.getStats().prefix
										+ item.getStats().speciality + " "
										+ item.getStats().itemName,
								statBox.x + 10, statBox.y + 20);
						g.setColor(Color.WHITE);
						g.drawString("Heal Power: "
								+ item.getStats().itemHealPower,
								statBox.x + 10, statBox.y + 35);
						g.drawString("Quality: " + item.getDegradation() + "/"
								+ item.getMaxDegradation(), statBox.x + 10,
								statBox.y + 50);
					}
				}
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
