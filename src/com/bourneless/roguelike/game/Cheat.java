package com.bourneless.roguelike.game;

import java.awt.event.KeyEvent;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.livingentity.mob.Mob;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.item.Boots;
import com.bourneless.roguelike.item.Food;
import com.bourneless.roguelike.item.Helmet;
import com.bourneless.roguelike.item.ItemType;
import com.bourneless.roguelike.item.Legs;
import com.bourneless.roguelike.item.Shield;
import com.bourneless.roguelike.item.Slot;
import com.bourneless.roguelike.item.Torso;
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
			if (cheat.length() > 0) {
				cheat = cheat.substring(0, cheat.length() - 1);
			}
		}

		cheat = cheat.toLowerCase();

		if (e.getKeyCode() == 10) {
			if (cheat.matches("givemeweapons")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Weapon(0));
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
					slot.setItem(new Food(0));
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
									Main.resourceLoader.monsterImages[0], instance);
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

			if (cheat.matches("headisbare")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Helmet(0));
				}
				stop();
			}

			if (cheat.matches("shieldme")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Shield(0));
				}
				stop();
			}

			if (cheat.matches("ilikenicechests")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Torso(0));
				}
				stop();
			}

			if (cheat.matches("allmenmustdie")) {
				for (int i = 0; i < instance.getMap().getTiles().length; i++) {
					for (int j = 0; j < instance.getMap().getTiles()[i].length; j++) {
						if (instance.getMap().getTiles()[i][j].hasEntity()) {
							for (Entity en : instance.getMap().getTiles()[i][j]
									.getEntities()) {
								if (en.getType() == EntityType.ENEMY) {
									Mob mob = (Mob) en;
									mob.kill();
								}
							}
						}
					}
				}
			}

			if (cheat.matches("protectmyknees")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Legs(0));
				}
				stop();
			}

			if (cheat.matches("shoeshopping")) {
				for (Slot slot : player.getInventory().getSlots()) {
					slot.setItem(new Boots(0));
				}
				stop();
			}

			if (cheat.matches("kitme")) {
				player.getInventory().getSlots()[0].setItem(new Weapon(998));
				player.getInventory().getSlots()[1].setItem(new Torso(998));
				player.getInventory().getSlots()[2].setItem(new Legs(998));
				player.getInventory().getSlots()[3].setItem(new Boots(998));
				player.getInventory().getSlots()[4].setItem(new Helmet(998));
				player.getInventory().getSlots()[5].setItem(new Shield(998));
				player.getInventory().getSlots()[6].setItem(new Food(998));
				stop();
			}

			if (cheat.matches("sickofthisplace")) {
				for (int i = 0; i < instance.getMap().getTiles().length; i++) {
					for (int j = 0; j < instance.getMap().getTiles()[i].length; j++) {
						if (instance.getMap().getTiles()[i][j].hasEntity()) {
							for (Entity ent : instance.getMap().getTiles()[i][j]
									.getEntities()) {
								if (ent.getName().matches("exit")) {
									player.setTile(instance.getMap().getTiles()[i][j + 1]);
									player.setLayer(instance.getMap()
											.getTiles()[i][j + 1].getTileY());
									instance.xOffset = Main.GAME_WIDTH / 2
											- player.getPos().x;
									instance.yOffset = Main.GAME_HEIGHT / 2
											- player.getPos().y;
									stop();
								}
							}
						}
					}
				}
			}

			stop();
		}

	}

	public boolean hasStarted() {
		return started;
	}
}
