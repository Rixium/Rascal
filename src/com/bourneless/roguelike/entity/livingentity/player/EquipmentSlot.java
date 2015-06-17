package com.bourneless.roguelike.entity.livingentity.player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.item.Item;
import com.bourneless.roguelike.item.ItemType;

public class EquipmentSlot {

	private Item item;
	private int type;

	private Player player;

	private Vector2 pos;
	private Rectangle rect;

	private boolean popup;

	private int statBoxWidth = 199;
	private int statBoxHeight = 100;
	private Vector2 statBox = new Vector2(Main.GAME_WIDTH - statBoxWidth - 10,
			Main.GAME_HEIGHT - statBoxHeight - 10);
	private Color statBoxColour = new Color(14796090);

	private int slotSize = 32;

	public EquipmentSlot(int type, Player player) {
		this.type = type;
		this.player = player;
	}

	public Item getItem() {
		return item;
	}

	public void giveItem(Item item) {
		this.item = item;
	}

	public boolean hasItem() {
		if (item == null) {
			return false;
		} else {
			return true;
		}
	}

	public void paint(Graphics2D g) {

		g.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, 0.7f));
		g.setColor(Color.black);
		g.fillRect(pos.x, pos.y, slotSize, slotSize);

		if (item != null) {
			item.drawButton(pos, g);
		}

		if (item != null) {
			g.setColor(Color.black);
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

				if (item.getStats().itemType != ItemType.FOOD) {
					if (item != null) {
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
					}
				}
			}
		}
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
		this.rect = new Rectangle(pos.x, pos.y, slotSize, slotSize);
	}

	public Rectangle getRect() {
		return this.rect;
	}

	public int getType() {
		return this.type;
	}

	public void removeItem() {
		this.item = null;
	}

	public void showPopup() {
		this.popup = true;
	}

	public void hidePopup() {
		this.popup = false;
	}
}
