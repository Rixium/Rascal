package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Helmet extends Item {

	public Helmet() {
		stats = new ItemStats(this);
		stats.itemName = "Helmet";
		stats.itemType = ItemType.HELMET;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
		stats.createStats();
	}
}
