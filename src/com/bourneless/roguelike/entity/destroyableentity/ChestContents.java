package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.item.ItemDropper;
import com.bourneless.roguelike.item.Slot;

public class ChestContents {

	private Slot[] slots = new Slot[5];
	private ItemDropper iDropper = new ItemDropper();
	private Vector2 pos;

	public ChestContents() {
		pos = new Vector2(20 + 32 * 5 / 2 + Main.GAME_WIDTH / 2,
				Main.GAME_HEIGHT / 2
						- Main.resourceLoader.inventoryScreen.getHeight() / 2
						+ 10 - 50);
		for (int i = 0; i < slots.length; i++) {
			slots[i] = new Slot(new Vector2(i * 32 + pos.x, pos.y), 32);
			slots[i].setItem(iDropper.dropItem());
		}
	}

	public void paint(Graphics2D g) {
		g.drawImage(Main.resourceLoader.chestScreen, pos.x - 5, pos.y - 5, null);
		for (int i = 0; i < slots.length; i++) {
			slots[i].paint(g);
		}
	}

	public Slot[] getSlots() {
		return this.slots;
	}

}
