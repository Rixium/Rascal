package com.bourneless.roguelike.entity.livingentity.player;

import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.item.Item;
import com.bourneless.roguelike.item.Slot;

public class Inventory {

	private int slotCount = 20;
	private int slotSize = 32;

	private Slot[] slots = new Slot[slotCount];

	public Inventory() {
		int y = 0;
		int x = 0;

		for (int i = 0; i < slotCount; i++) {
			slots[i] = new Slot(
					new Vector2(x * slotSize - slotSize * 5 / 2
							+ Main.GAME_WIDTH / 2
							+ Main.resourceLoader.inventoryScreen.getWidth(), y
							* slotSize + Main.GAME_HEIGHT / 2
							- Main.resourceLoader.inventoryScreen.getHeight()
							/ 2 + 10), 32);

			x++;
			if (x == 5 || x == 10 || x == 15 || x == 20) {
				y++;
				x = 0;
			}
		}
	}

	public void paint(Graphics2D g) {
		g.drawImage(
				Main.resourceLoader.inventoryScreen,
				Main.GAME_WIDTH / 2
						- Main.resourceLoader.inventoryScreen.getWidth() / 2
						+ Main.resourceLoader.inventoryScreen.getWidth(),
				Main.GAME_HEIGHT / 2
						- Main.resourceLoader.inventoryScreen.getHeight() / 2,
				null);

		for (Slot slot : slots) {
			slot.paint(g);
		}

	}

	public void addItem(Item item) {
		for (int i = 0; i < slots.length; i++) {
			if (slots[i].isFree()) {
				slots[i].setItem(item);
				break;
			}
		}
	}

	public Slot[] getSlots() {
		return this.slots;
	}
}
