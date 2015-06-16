package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Legs extends Item {

	public Legs(int rarity) {
		stats = new ItemStats(this);
		stats.itemName = "Legs";
		stats.itemType = ItemType.LEGS;
		stats.createStats(rarity);
		this.itemInvImage = Main.resourceLoader.legImages[stats.rarity - 1];
	}
}
