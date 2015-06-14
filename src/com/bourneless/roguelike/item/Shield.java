package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Shield extends Item {
	
	public Shield() {
		stats = new ItemStats(this);
		stats.itemName = "Shield";
		stats.itemType = ItemType.SHIELD;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
		stats.createStats();
	}

}
