package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Boots extends Item {
	
	public Boots() {
		stats = new ItemStats(this);
		stats.itemName = "Boots";
		stats.itemType = ItemType.BOOTS;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
	}

}
