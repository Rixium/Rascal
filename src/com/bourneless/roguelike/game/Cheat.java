package com.bourneless.roguelike.game;

import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.livingentity.mob.Mob;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.item.Food;
import com.bourneless.roguelike.item.ItemType;
import com.bourneless.roguelike.item.Slot;
import com.bourneless.roguelike.item.Weapon;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;

public class Cheat {

	private boolean started = false;

	String cheat = "";
	Player player;
	Instance instance;

	public Cheat(Player player, Instance instance) {
		this.player = player;
		this.instance = instance;
	}

	public void start() {
		started = true;
	}

	public void stop() {
		cheat = "";
		started = false;
	}

	public void addKey(KeyEvent e) {
		if (e.getKeyCode() != 16 && e.getKeyCode() != 10 && e.getKeyCode() != 8) {
			cheat += (char) e.getKeyCode();
		}

		if (e.getKeyCode() == 8) {
			cheat = cheat.substring(0, cheat.length() - 1);
		}

		cheat = cheat.toLowerCase();

		if (e.getKeyCode() == 10) {
			if (cheat.matches("givemeweapons")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Weapon());
				}
				stop();
			}

			if (cheat.matches("givemehealth")) {
				player.setHealth(player.getMaxHealth());
				stop();
			}

			if (cheat.matches("ding")) {
				player.getStats().addExperience(
						player.getStats().expToLevel
								- player.getStats().experience);
				stop();
			}

			if (cheat.matches("letmelive")) {
				player.setHealth(player.getMaxHealth());
				instance.setShowDeathScreen(false);
				stop();
			}

			if (cheat.matches("imfat")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Food());
				}
				stop();
			}

			if (cheat.matches("foodisfresh")) {
				for (Slot slot : player.getInventory().getSlots()) {
					if (slot.getItem() != null) {
						if (slot.getItem().getStats().itemType == ItemType.FOOD) {
							Food food = (Food) slot.getItem();
							food.setDegradation(0);
						}
					}
				}
				stop();
			}

			if (cheat.matches("imnotscared")) {
				for (int i = 0; i < instance.getMap().getTiles().length; i++) {
					for (int j = 0; j < instance.getMap().getTiles()[i].length; j++) {
						if (!instance.getMap().getTiles()[i][j].hasEntity()
								&& instance.getMap().getTiles()[i][j]
										.getTileClass() == TileClass.FLOOR) {
							Mob monster = new Mob(
									instance.getMap().getTiles()[i][j],
									Main.resourceLoader.monsterImages[0]);
							instance.getMap().getTiles()[i][j].setLayer(0);
							instance.getMap().getTiles()[i][j]
									.addEntity(monster);
							instance.getMap().getEntityList().add(monster);
						}
					}
				}

				for (int i = 0; i < instance.getMap().getEntityList().size(); i++) {
					instance.getMap()
							.getEntityList()
							.get(i)
							.setLayer(
									instance.getMap().getEntityList().get(i)
											.getTile().getTileY());
				}

				stop();
			}

			stop();
		}

	}

	public boolean hasStarted() {
		return started;
	}
}
