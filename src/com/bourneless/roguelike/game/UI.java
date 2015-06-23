package com.bourneless.roguelike.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.engine.util.Button;
import com.bourneless.roguelike.entity.destroyableentity.Chest;
import com.bourneless.roguelike.entity.livingentity.player.EquipmentSlot;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.feature.debuff.HealthDebuff;
import com.bourneless.roguelike.item.Food;
import com.bourneless.roguelike.item.Item;
import com.bourneless.roguelike.item.ItemType;
import com.bourneless.roguelike.item.Slot;

public class UI {

	private Random random = new Random();
	private Player player;
	private Font font;

	private int playerPortrait;
	private boolean showCharacterScreen = false;
	private boolean showInventory = false;
	private boolean levelUpStart = false;

	private boolean holdingItem = false;
	private Item heldItem;

	private int slotSize = 32;

	private Chest chest;

	private Rectangle mouseRect = new Rectangle(0, 0, 0, 0);
	private Button binButton = new Button(Main.resourceLoader.binImage,
			new Vector2(Main.GAME_WIDTH / 2
					- Main.resourceLoader.inventoryScreen.getWidth() / 2
					+ Main.resourceLoader.inventoryScreen.getWidth()
					+ Main.resourceLoader.inventoryScreen.getWidth() + 5,
					Main.GAME_HEIGHT / 2
							+ Main.resourceLoader.inventoryScreen.getHeight()
							/ 2 - Main.resourceLoader.binImage.getHeight()));

	private Button[] levelButtons = new Button[10];

	public UI(Player player) {
		font = new Font("A Font With Serifs", Font.PLAIN, 18);
		this.player = player;
		playerPortrait = random
				.nextInt(Main.resourceLoader.playerPortraits.length);

		levelButtons[0] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 - 8));
		levelButtons[0].setPopupText("Increases the power of your hit.");
		levelButtons[1] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 12));
		levelButtons[1].setPopupText("Raises your maximum health.");
		levelButtons[2] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 32));
		levelButtons[2]
				.setPopupText("Increases your maximum defence to creature attacks.");
		levelButtons[3] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 52));
		levelButtons[3].setPopupText("Increases the chance of a higher hit.");
		levelButtons[4] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 72));
		levelButtons[4]
				.setPopupText("Increases the chance to develop new skills.");
		levelButtons[5] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 92));
		levelButtons[5].setPopupText("Lowers the prices of merchants.");
		levelButtons[6] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 112));
		levelButtons[6].setPopupText("Increases your maximum mana pool.");
		levelButtons[7] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 132));
		levelButtons[7]
				.setPopupText("Increases your mental resistance to fear and pain.");
		levelButtons[8] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 152));
		levelButtons[8]
				.setPopupText("Increases your ability to sense traps and other dangers.");
		levelButtons[9] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 172));
		levelButtons[9]
				.setPopupText("Increases the chance to crit, and find better items.");

		player.getEquipment()[0].setPos(new Vector2(20
				+ Main.resourceLoader.statScreen.getWidth() / 2
				+ Main.resourceLoader.statScreen.getWidth() / 4 - slotSize / 2,
				Main.GAME_HEIGHT / 2 - slotSize));
		player.getEquipment()[1].setPos(new Vector2(20
				+ Main.resourceLoader.statScreen.getWidth() / 2
				+ Main.resourceLoader.statScreen.getWidth() / 4 - slotSize / 2
				+ Main.resourceLoader.inventoryPlayer.getWidth() / 2,
				Main.GAME_HEIGHT / 2 - slotSize / 2));
		player.getEquipment()[2].setPos(new Vector2(20
				+ Main.resourceLoader.statScreen.getWidth() / 2
				+ Main.resourceLoader.statScreen.getWidth() / 4 - slotSize / 2
				- Main.resourceLoader.inventoryPlayer.getWidth() / 2,
				Main.GAME_HEIGHT / 2 - slotSize / 2));
		player.getEquipment()[3].setPos(new Vector2(20
				+ Main.resourceLoader.statScreen.getWidth() / 2
				+ Main.resourceLoader.statScreen.getWidth() / 4 - slotSize / 2,
				Main.GAME_HEIGHT / 2
						- Main.resourceLoader.inventoryPlayer.getHeight() / 2
						+ slotSize));

		player.getEquipment()[4].setPos(new Vector2(20
				+ Main.resourceLoader.statScreen.getWidth() / 2
				+ Main.resourceLoader.statScreen.getWidth() / 4 - slotSize / 2,
				Main.GAME_HEIGHT / 2 + slotSize));
		player.getEquipment()[5].setPos(new Vector2(20
				+ Main.resourceLoader.statScreen.getWidth() / 2
				+ Main.resourceLoader.statScreen.getWidth() / 4 - slotSize / 2,
				Main.GAME_HEIGHT / 2
						+ Main.resourceLoader.inventoryPlayer.getHeight() / 2
						- slotSize));

	}

	public void update() {

	}

	public void paint(Graphics2D g) {

		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(player.getStats().getName() + " the "
				+ player.getStats().getTitle() + " of "
				+ player.getStats().getSpeciality(),
				20 + Main.resourceLoader.playerPortraits[playerPortrait]
						.getWidth(), 25);

		// Draw Health
		g.setColor(Color.RED);
		g.fillRect(
				10 + Main.resourceLoader.playerPortraits[playerPortrait]
						.getWidth(),
				20
						+ Main.resourceLoader.playerPortraits[playerPortrait]
								.getHeight() / 2
						- Main.resourceLoader.healthBar.getHeight(),
				player.getHealth() * Main.resourceLoader.healthBar.getWidth()
						/ player.getMaxHealth(), Main.resourceLoader.healthBar
						.getHeight());

		g.drawImage(
				Main.resourceLoader.healthBar,
				10 + Main.resourceLoader.playerPortraits[playerPortrait]
						.getWidth(),
				20
						+ Main.resourceLoader.playerPortraits[playerPortrait]
								.getHeight() / 2
						- Main.resourceLoader.healthBar.getHeight(), null);

		// Draw Experience

		g.setColor(Color.YELLOW);

		g.fillRect(

				10 + Main.resourceLoader.playerPortraits[playerPortrait]
						.getWidth(),
				20
						+ Main.resourceLoader.playerPortraits[playerPortrait]
								.getHeight() / 2
						+ Main.resourceLoader.healthBar.getHeight() / 2, player
						.getStats().getExperience()
						* 200
						/ player.getStats().getExpToLevel(),
				Main.resourceLoader.healthBar.getHeight());

		g.drawImage(
				Main.resourceLoader.healthBar,
				10 + Main.resourceLoader.playerPortraits[playerPortrait]
						.getWidth(),
				20
						+ Main.resourceLoader.playerPortraits[playerPortrait]
								.getHeight() / 2
						+ Main.resourceLoader.healthBar.getHeight() / 2, null);

		// Draw Portrait

		g.drawImage(Main.resourceLoader.playerPortraits[playerPortrait], 10,
				20, null);

		// Draw level up animation

		if (player.getStats().getPoints() > 0) {
			if (!levelUpStart) {
				Main.resourceLoader.levelUpAnimation.start(false);
				levelUpStart = true;
			}
			Main.resourceLoader.levelUpAnimation.paint(g, new Vector2(20,
					20 + 10));
		} else {
			if (levelUpStart) {
				Main.resourceLoader.levelUpAnimation.stop();
				levelUpStart = false;
			}
		}

		if (showCharacterScreen) {
			g.drawImage(Main.resourceLoader.statScreen, 20, Main.GAME_HEIGHT
					/ 2 - Main.resourceLoader.statScreen.getHeight() / 2, null);

			// Left page

			g.setColor(Color.WHITE);
			g.drawString(
					"Strength: "
							+ player.getStats().baseStrength
							+ " +"
							+ (player.getStats().strength - player.getStats().baseStrength),
					80, Main.GAME_HEIGHT / 2);
			g.drawString("Constitution: " + player.getStats().baseConst, 80,
					Main.GAME_HEIGHT / 2 + 20);
			g.drawString(
					"Fortitude: "
							+ player.getStats().baseFort
							+ " +"
							+ (player.getStats().fortitude - player.getStats().baseFort),
					80, Main.GAME_HEIGHT / 2 + 40);
			g.drawString("Reflexes: " + player.getStats().baseRefl, 80,
					Main.GAME_HEIGHT / 2 + 60);
			g.drawString("Mind: " + player.getStats().baseMind, 80,
					Main.GAME_HEIGHT / 2 + 80);
			g.drawString("Presence: " + player.getStats().basePres, 80,
					Main.GAME_HEIGHT / 2 + 100);
			g.drawString("Spirit: " + player.getStats().baseSpir, 80,
					Main.GAME_HEIGHT / 2 + 120);
			g.drawString("Sanity: " + player.getStats().baseSan, 80,
					Main.GAME_HEIGHT / 2 + 140);
			g.drawString("Awareness: " + player.getStats().baseAware, 80,
					Main.GAME_HEIGHT / 2 + 160);
			g.drawString("Luck: " + player.getStats().baseLuck, 80,
					Main.GAME_HEIGHT / 2 + 180);

			// Right page

			g.setColor(Color.BLACK);

			String nameString = player.getStats().getName() + " the "
					+ player.getStats().getTitle() + " of "
					+ player.getStats().getSpeciality();
			int stringLength = (int) g.getFontMetrics()
					.getStringBounds(nameString, g).getWidth();
			int stringHeight = (int) g.getFontMetrics()
					.getStringBounds(nameString, g).getHeight();

			int start = 20 + Main.resourceLoader.statScreen.getWidth() / 2
					+ Main.resourceLoader.statScreen.getWidth() / 4
					- stringLength / 2;

			int xPos = Main.GAME_HEIGHT / 2
					- Main.resourceLoader.statScreen.getHeight() / 3;

			g.drawString(nameString, start, xPos);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.7f));

			g.drawImage(
					Main.resourceLoader.inventoryPlayer,
					20 + Main.resourceLoader.statScreen.getWidth() / 2
							+ Main.resourceLoader.statScreen.getWidth() / 4
							- Main.resourceLoader.inventoryPlayer.getWidth()
							/ 2 - 4,
					Main.GAME_HEIGHT / 2
							- Main.resourceLoader.inventoryPlayer.getHeight()
							/ 2, null);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.8f));
			g.setColor(Color.BLACK);

			for (EquipmentSlot e : player.getEquipment()) {
				e.paint(g);
			}

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1));

			// Left Side Buttons

			if (player.getStats().getPoints() > 0) {
				for (Button button : levelButtons) {
					button.paint(g);
				}
			}

			if (chest != null) {
				if (!chest.getShowChest()) {
					chest.openChest();
				}
			}

		}

		if (showInventory) {
			player.getInventory().paint(g);
			binButton.paint(g);
		}

		if (holdingItem) {
			if (heldItem != null) {
				g.drawImage(heldItem.getItemInvImage(), mouseRect.x,
						mouseRect.y, null);
			}
		}

	}

	public void showCScreen(boolean bool) {
		this.showCharacterScreen = bool;

		if (bool) {
			Main.resourceLoader.playClip(Main.resourceLoader.openBook, 1f,
					false);
		} else if (!bool) {
			Main.resourceLoader.playClip(Main.resourceLoader.closeBook, 1f,
					false);
		}
	}

	public boolean getShowingCScreen() {
		return this.showCharacterScreen;
	}

	public void showInventoryScreen(boolean bool) {
		this.showInventory = bool;
	}

	public boolean getShowingInventoryScreen() {
		return this.showInventory;
	}

	public void mouseMoved(Rectangle mouseRect) {
		this.mouseRect = mouseRect;

		for (Button button : levelButtons) {
			if (mouseRect.intersects(button.getRect())) {
				button.showPopup();
			} else {
				button.hidePopup();
			}
		}

		for (Slot slot : player.getInventory().getSlots()) {
			if (mouseRect.intersects(slot.getRect())) {
				slot.showPopup();
			} else {
				slot.hidePopup();
			}
		}

		for (EquipmentSlot slot : player.getEquipment()) {
			if (mouseRect.intersects(slot.getRect())) {
				slot.showPopup();
			} else {
				slot.hidePopup();
			}
		}

		if (chest != null) {
			for (Slot slot : chest.getContents().getSlots()) {
				if (mouseRect.intersects(slot.getRect())) {
					slot.showPopup();
				} else {
					slot.hidePopup();
				}
			}
		}
	}

	public void setChest(Chest chest) {
		if (this.chest == null) {
			this.chest = chest;
			this.showInventory = true;
		} else {
			this.chest.closeChest();
			this.chest = null;
			this.chest = chest;
			this.showInventory = true;
		}
	}

	public void mousePressed(Rectangle mouseRect) {
		if (showCharacterScreen) {
			// Left Side
			if (player.getStats().getPoints() > 0) {
				if (levelButtons[0].getRect().contains(mouseRect)) {
					player.getStats().baseStrength += 1;
					player.getStats().removePoint();
				} else if (levelButtons[1].getRect().contains(mouseRect)) {
					player.getStats().baseConst += 1;
					player.setMaxHealth(player.getStats().constitution * 100);
					player.getStats().removePoint();
				} else if (levelButtons[2].getRect().contains(mouseRect)) {
					player.getStats().baseFort += 1;
					player.getStats().removePoint();
				} else if (levelButtons[3].getRect().contains(mouseRect)) {
					player.getStats().baseRefl += 1;
					player.getStats().removePoint();
				} else if (levelButtons[4].getRect().contains(mouseRect)) {
					player.getStats().baseMind += 1;
					player.getStats().removePoint();
				} else if (levelButtons[5].getRect().contains(mouseRect)) {
					player.getStats().basePres += 1;
					player.getStats().removePoint();
				} else if (levelButtons[6].getRect().contains(mouseRect)) {
					player.getStats().baseSpir += 1;
					player.getStats().removePoint();
				} else if (levelButtons[7].getRect().contains(mouseRect)) {
					player.getStats().baseSan += 1;
					player.getStats().removePoint();
				} else if (levelButtons[8].getRect().contains(mouseRect)) {
					player.getStats().baseAware += 1;
					player.getStats().removePoint();
				} else if (levelButtons[9].getRect().contains(mouseRect)) {
					player.getStats().baseLuck += 1;
					player.getStats().removePoint();
				}
				player.getStats().checkStats(player.getEquipment());
			}

			// Right Side

			if (holdingItem) {
				if (player.getEquipment()[0].getRect().contains(mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[0]
							.getType()) {
						if (heldItem.getStats().level <= player.getStats().level) {
							if (!player.getEquipment()[0].hasItem()) {
								player.getEquipment()[0].giveItem(heldItem);
								heldItem = null;
								holdingItem = false;
							} else {
								Item newItem = player.getEquipment()[0]
										.getItem();
								player.getEquipment()[0].giveItem(heldItem);
								heldItem = newItem;
							}
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								if (s.getItem() == null) {
									s.setItem(heldItem);
									heldItem = null;
									holdingItem = false;
								}
							}
						}
					}
				} else if (player.getEquipment()[1].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[1]
							.getType()) {
						if (heldItem.getStats().level <= player.getStats().level) {
							if (!player.getEquipment()[1].hasItem()) {
								player.getEquipment()[1].giveItem(heldItem);
								heldItem = null;
								holdingItem = false;
							} else {
								Item newItem = player.getEquipment()[1]
										.getItem();
								player.getEquipment()[1].giveItem(heldItem);
								heldItem = newItem;
							}
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								if (s.getItem() == null) {
									s.setItem(heldItem);
									heldItem = null;
									holdingItem = false;
								}
							}
						}
					}
				} else if (player.getEquipment()[2].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[2]
							.getType()) {
						if (heldItem.getStats().level <= player.getStats().level) {
							if (!player.getEquipment()[2].hasItem()) {
								player.getEquipment()[2].giveItem(heldItem);
								heldItem = null;
								holdingItem = false;
							} else {
								Item newItem = player.getEquipment()[2]
										.getItem();
								player.getEquipment()[2].giveItem(heldItem);
								heldItem = newItem;
							}
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								if (s.getItem() == null) {
									s.setItem(heldItem);
									heldItem = null;
									holdingItem = false;
								}
							}
						}
					}
				} else if (player.getEquipment()[3].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[3]
							.getType()) {
						if (heldItem.getStats().level <= player.getStats().level) {
							if (!player.getEquipment()[3].hasItem()) {
								player.getEquipment()[3].giveItem(heldItem);
								heldItem = null;
								holdingItem = false;
							} else {
								Item newItem = player.getEquipment()[3]
										.getItem();
								player.getEquipment()[3].giveItem(heldItem);
								heldItem = newItem;
							}
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								if (s.getItem() == null) {
									s.setItem(heldItem);
									heldItem = null;
									holdingItem = false;
								}
							}
						}
					}
				} else if (player.getEquipment()[4].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[4]
							.getType()) {
						if (heldItem.getStats().level <= player.getStats().level) {
							if (!player.getEquipment()[4].hasItem()) {
								player.getEquipment()[4].giveItem(heldItem);
								heldItem = null;
								holdingItem = false;
							} else {
								Item newItem = player.getEquipment()[4]
										.getItem();
								player.getEquipment()[4].giveItem(heldItem);
								heldItem = newItem;
							}
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								if (s.getItem() == null) {
									s.setItem(heldItem);
									heldItem = null;
									holdingItem = false;
								}
							}
						}
					}
				} else if (player.getEquipment()[5].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[5]
							.getType()) {
						if (heldItem.getStats().level <= player.getStats().level) {
							if (!player.getEquipment()[5].hasItem()) {
								player.getEquipment()[5].giveItem(heldItem);
								heldItem = null;
								holdingItem = false;
							} else {
								Item newItem = player.getEquipment()[5]
										.getItem();
								player.getEquipment()[5].giveItem(heldItem);
								heldItem = newItem;
							}
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								if (s.getItem() == null) {
									s.setItem(heldItem);
									heldItem = null;
									holdingItem = false;
								}
							}
						}
					}
				}
			} else {
				for (EquipmentSlot es : player.getEquipment()) {
					if (es.getRect().contains(mouseRect)) {
						if (es.hasItem()) {
							heldItem = es.getItem();
							es.removeItem();
							holdingItem = true;
						}
					}
				}
			}
		}

		if (showInventory) {

			if (binButton.getRect().contains(mouseRect)) {
				if (holdingItem) {
					heldItem = null;
					holdingItem = false;
				}
			}

			checkSlots: for (Slot slot : player.getInventory().getSlots()) {
				if (slot.getRect().contains(mouseRect)) {
					if (slot.getItem() != null && !holdingItem) {
						if (slot.getItem().getStats().itemType == ItemType.FOOD) {
							Food food = (Food) slot.getItem();
							player.addHealth(food.getStats().itemHealPower);
							if (food.getDegradation() >= food
									.getMaxDegradation()) {
								Main.resourceLoader
										.playClip(
												Main.resourceLoader.playerSick[random
														.nextInt(Main.resourceLoader.playerSick.length)],
												1f, false);
								player.addDebuff(new HealthDebuff(10));
							} else {
								Main.resourceLoader
										.playClip(
												Main.resourceLoader.eatSounds[random
														.nextInt(Main.resourceLoader.eatSounds.length)],
												1f, false);
							}
							slot.removeItem();
							break checkSlots;
						} else {
							holdingItem = true;
							heldItem = slot.getItem();
							slot.removeItem();
							break checkSlots;
						}
					} else if (holdingItem) {
						if (slot.getItem() == null) {
							slot.setItem(heldItem);
							heldItem = null;
							holdingItem = false;
							break checkSlots;
						} else {
							for (Slot s : player.getInventory().getSlots()) {
								s.setItem(heldItem);
								heldItem = null;
								holdingItem = false;
								break checkSlots;
							}
						}
					}
				}
			}

			checkChest: if (chest != null) {

				if (chest.getCloseButton().getRect().intersects(mouseRect)) {
					chest.closeChest();
					chest = null;
					break checkChest;
				}

				checkSlots: for (Slot s : chest.getContents().getSlots()) {
					if (s.getRect().contains(mouseRect)) {
						if (!holdingItem) {
							heldItem = s.getItem();
							s.removeItem();
							holdingItem = true;
							break checkSlots;
						} else if (holdingItem) {
							if (s.isFree()) {
								s.setItem(heldItem);
								heldItem = null;
								holdingItem = false;
							}
							break checkSlots;
						}
					}
				}
			}
		}
	}

	public Chest getChest() {
		return this.chest;
	}
}
