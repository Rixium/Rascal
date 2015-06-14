package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Weapon extends Item {

	public Weapon() {
		stats = new ItemStats(this);
		stats.itemName = "Weapon";
		stats.itemType = ItemType.WEAPON;
		this.itemInvImage = Main.resourceLoader.itemInvImages[stats.itemType];
		stats.createStats();
	}

}
