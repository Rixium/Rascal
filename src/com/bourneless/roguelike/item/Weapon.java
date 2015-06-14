package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Weapon extends Item {

	public Weapon() {
		stats = new ItemStats(this);
		stats.itemName = "Weapon";
		stats.itemType = ItemType.WEAPON;
		stats.createStats();
		this.itemInvImage = Main.resourceLoader.swordImages[stats.rarity - 1];
	}

}
