package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Food extends Item {

	public Food() {
		stats = new ItemStats(this);
		stats.itemName = "Food";
		stats.itemType = ItemType.FOOD;
		this.itemInvImage = Main.resourceLoader.foodImages[0];
		stats.createStats();
	}

	@Override
	public void degrade() {
		if (!hasDegraded) {
			this.degradation++;
			if (this.degradation >= 20) {
				this.stats.itemHealPower /= 2;
				this.itemInvImage = Main.resourceLoader.foodImages[1];
				hasDegraded = true;
			}
		}
	}
}
