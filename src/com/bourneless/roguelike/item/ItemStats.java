package com.bourneless.roguelike.item;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Random;
import com.bourneless.roguelike.screen.GameScreen;

public class ItemStats {

	public String itemName;
	public String speciality;
	public String prefix;

	public int rarity;
	public int level;

	public int itemQuality;

	public int itemStrength;
	public int itemFortitude;
	public int itemReflexes;
	public int itemConstitution;

	public int originalHealPower;
	public int itemHealPower;

	public int itemType = 0;

	public ItemStats(Item item) {

	}

	public void createStats(int r) {
		int getRarity = r;

		if (getRarity == 0) {
			getRarity = Random.getRandom(1000);
		}

		prefix = "";

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
			prefix = "Rare ";
		} else if (getRarity >= 990 && getRarity < 998) {
			rarity = 7;
			prefix = "Elite ";
		} else if (getRarity >= 998) {
			rarity = 8;
			prefix = "Epic ";
		}

		if (itemType == ItemType.FOOD) {
			itemHealPower = rarity * 100;
			originalHealPower = itemHealPower;
		} else {
			GameScreen screen = (GameScreen) Main.game.getScreen();

			level = Random.getRandom(screen.getInstance().getPlayer()
					.getStats().level + 2);

			int points = level * rarity;

			if (points == 0) {
				points = 1;
			}

			while (points > 0) {
				int ranSkill = Random.getRandom(4);
				switch (ranSkill) {
				case 0:
					itemStrength++;
					points--;
					break;
				case 1:
					itemFortitude++;
					points--;
					break;
				case 2:
					itemReflexes++;
					points--;
					break;
				case 3:
					itemConstitution++;
					points--;
					break;
				default:
					break;
				}
			}

			int highestSkill = Math.max(
					itemStrength,
					Math.max(itemFortitude,
							Math.max(itemReflexes, itemConstitution)));

			if (highestSkill == itemStrength) {
				speciality = "Power";
			} else if (highestSkill == itemFortitude) {
				speciality = "Protection";
			} else if (highestSkill == itemReflexes) {
				speciality = "Swiftness";
			} else if (highestSkill == itemConstitution) {
				speciality = "Physique";
			}

		}
	}
}
