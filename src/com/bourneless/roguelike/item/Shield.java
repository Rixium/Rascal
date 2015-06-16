package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Shield extends Item {
	
	public Shield(int rarity) {
		stats = new ItemStats(this);
		stats.itemName = "Shield";
		stats.itemType = ItemType.SHIELD;
		stats.createStats(rarity);
		this.itemInvImage = Main.resourceLoader.shieldImages[stats.rarity - 1];
	}

}
