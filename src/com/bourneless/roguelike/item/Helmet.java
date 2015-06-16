package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Helmet extends Item {

	public Helmet(int rarity) {
		stats = new ItemStats(this);
		stats.itemName = "Helmet";
		stats.itemType = ItemType.HELMET;
		stats.createStats(rarity);
		this.itemInvImage = Main.resourceLoader.helmetImages[stats.rarity - 1];
	}
}
