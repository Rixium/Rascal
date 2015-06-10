package com.bourneless.roguelike.item;

import com.bourneless.engine.math.Random;
import com.bourneless.roguelike.entity.livingentity.player.Player;

public class ItemDropper {

	public ItemDropper() {

	}

	public void dropItem(Player player) {
		Item item = null;

		int itemType = Random.getRandom(ItemType.ITEM_TYPE_COUNT);

		switch (itemType) {
		case ItemType.WEAPON:
			item = new Weapon();
			break;

		case ItemType.HELMET:
			item = new Helmet();
			break;

		case ItemType.FOOD:
			item = new Food();
			break;

		case ItemType.LEGS:
			item = new Legs();
			break;

		case ItemType.SHIELD:
			item = new Shield();
			break;

		case ItemType.TORSO:
			item = new Torso();
			break;

		case ItemType.BOOTS:
			item = new Boots();
			break;

		default:
			break;
		}

		System.out.println("Giving " + player.getStats().getName() + " a "
				+ item.getStats().itemName + "!");

		player.getInventory().addItem(item);
	}
}
