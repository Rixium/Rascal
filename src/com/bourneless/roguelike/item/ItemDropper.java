package com.bourneless.roguelike.item;

import com.bourneless.engine.math.Random;
import com.bourneless.roguelike.entity.livingentity.player.Player;

public class ItemDropper {

	public ItemDropper() {

	}

	public void dropItem(Player player) {
		Item item = null;

		int itemType = 0;

		int isFood = Random.getRandom(5);
		if (isFood > 1) {
			itemType = ItemType.FOOD;
		} else {
			itemType = Random.getRandom(ItemType.ITEM_TYPE_COUNT);
		}

		switch (itemType) {
		case ItemType.WEAPON:
			item = new Weapon(0);
			break;

		case ItemType.HELMET:
			item = new Helmet(0);
			break;

		case ItemType.FOOD:
			item = new Food(0);
			break;

		case ItemType.LEGS:
			item = new Legs(0);
			break;

		case ItemType.SHIELD:
			item = new Shield(0);
			break;

		case ItemType.TORSO:
			item = new Torso(0);
			break;

		case ItemType.BOOTS:
			item = new Boots(0);
			break;
		default:
			break;
		}

		System.out.println("Giving " + player.getStats().getName() + " a "
				+ item.getStats().itemName + "!");

		player.getInventory().addItem(item);
	}
}
