package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;

public class Food extends Item {

	private int degredationScale = 30;

	public Food(int rarity) {
		stats = new ItemStats(this);
		stats.itemName = "Food";
		stats.itemType = ItemType.FOOD;
		this.itemInvImage = Main.resourceLoader.foodImages[0];
		stats.createStats(rarity);

		stats.speciality = "Fresh";

		this.maxDegradation = stats.rarity * degredationScale;
	}

	@Override
	public void degrade() {
		if (!hasDegraded) {
			this.degradation++;
			if (this.degradation >= this.maxDegradation) {
				stats.speciality = "Rotten";
				this.stats.itemHealPower /= 2;
				this.itemInvImage = Main.resourceLoader.foodImages[1];
				hasDegraded = true;
			}
		}
	}

	public void setDegradation(int deg) {
		this.degradation = deg;
		if (deg < this.maxDegradation) {
			stats.speciality = "Fresh";
			this.stats.itemHealPower = this.stats.originalHealPower;
			this.itemInvImage = Main.resourceLoader.foodImages[0];
			hasDegraded = false;
		}
	}

}
