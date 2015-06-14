package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Random;
import com.bourneless.roguelike.screen.GameScreen;

public class ItemStats {

	public String itemName;
	public String speciality;

	public int rarity;

	public int itemQuality;

	public int itemStrength;
	public int itemDefence;

	public int itemHealPower;

	public int itemType = 0;

	public ItemStats(Item item) {

	}

	public void createStats() {

		int getRarity = Random.getRandom(1000);

		if (getRarity < 600) {
			rarity = 1;
		} else if (getRarity >= 600 && getRarity < 800) {
			rarity = 2;
		} else if (getRarity >= 800 && getRarity < 900) {
			rarity = 3;
		} else if (getRarity >= 900 && getRarity < 950) {
			rarity = 4;
		} else if (getRarity >= 950 && getRarity < 975) {
			rarity = 5;
		} else if (getRarity >= 975 && getRarity < 990) {
			rarity = 6;
		} else if (getRarity >= 990 && getRarity < 998) {
			rarity = 7;
		} else if (getRarity >= 998) {
			rarity = 8;
		}

		System.out.println(rarity);
		if (itemType == ItemType.FOOD) {
			itemHealPower = rarity * 100;
		} else {
			GameScreen screen = (GameScreen) Main.game.getScreen();

			int strOrDef = Random.getRandom(10);
			if (strOrDef >= 0 && strOrDef < 4) {
				itemStrength = rarity
						+ (Random.getRandom(screen.getInstance().getPlayer()
								.getStats().level
								+ 1
								* Random.getRandom(screen.getInstance()
										.getFloor() + 1)));
				itemDefence = 0;
			} else if (strOrDef >= 4 && strOrDef < 8) {
				itemDefence = rarity
						+ (Random.getRandom(screen.getInstance().getPlayer()
								.getStats().level
								+ 1
								* Random.getRandom(screen.getInstance()
										.getFloor() + 1)));
				itemStrength = 0;
			} else {
				itemStrength = rarity
						+ (Random.getRandom(screen.getInstance().getPlayer()
								.getStats().level
								+ 1
								* Random.getRandom(screen.getInstance()
										.getFloor() + 1)));
				itemDefence = rarity
						+ (Random.getRandom(screen.getInstance().getPlayer()
								.getStats().level
								+ 1
								* Random.getRandom(screen.getInstance()
										.getFloor() + 1)));
			}

			if (itemStrength > itemDefence) {
				speciality = "Powerful";
			} else if (itemDefence > itemStrength) {
				speciality = "Defender";
			} else if (itemDefence == itemStrength && itemDefence > 1) {
				speciality = "Hero";
			} else {
				speciality = "Peasant";
			}

			if (rarity == 8) {
				speciality = "Almighty";
			}
		}
	}
}
