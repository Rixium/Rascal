package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Torso extends Item {
	
	public Torso(int rarity) {
		stats = new ItemStats(this);
		stats.itemName = "Torso";
		stats.itemType = ItemType.TORSO;
		stats.createStats(rarity);
		this.itemInvImage = Main.resourceLoader.torsoImages[stats.rarity - 1];
	}

}
