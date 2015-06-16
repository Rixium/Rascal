package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Boots extends Item {

	public Boots(int rarity) {
		stats = new ItemStats(this);
		stats.itemName = "Boots";
		stats.itemType = ItemType.BOOTS;
		stats.createStats(rarity);
		this.itemInvImage = Main.resourceLoader.bootImages[stats.rarity - 1];
	}

}
