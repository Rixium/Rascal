package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Torso extends Item {
	
	public Torso() {
		stats = new ItemStats(this);
		stats.itemName = "Torso";
		stats.itemType = ItemType.TORSO;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
	}

}
