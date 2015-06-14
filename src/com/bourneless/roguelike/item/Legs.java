package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Legs extends Item {
	
	public Legs() {
		stats = new ItemStats(this);
		stats.itemName = "Legs";
		stats.itemType = ItemType.LEGS;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
		stats.createStats();
	}

}
