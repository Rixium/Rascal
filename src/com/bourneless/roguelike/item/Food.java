package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Food extends Item {
	
	public Food() {
		stats = new ItemStats(this);
		stats.itemName = "Food";
		stats.itemType = ItemType.FOOD;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
	}

}
