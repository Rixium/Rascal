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
import com.bourneless.roguelike.entity.livingentity.player.EquipmentSlot;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.item.Item;
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

	private Rectangle mouseRect = new Rectangle(0, 0, 0, 0);

	private Button[] levelButtons = new Button[10];

	public UI(Player player) {
		font = new Font("A Font With Serifs", Font.PLAIN, 18);
		this.player = player;
		playerPortrait = random
				.nextInt(Main.resourceLoader.playerPortraits.length);

		levelButtons[0] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 - 8));
		levelButtons[1] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 12));
		levelButtons[2] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 32));
		levelButtons[3] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 52));
		levelButtons[4] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 72));
		levelButtons[5] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 92));
		levelButtons[6] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 112));
		levelButtons[7] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 132));
		levelButtons[8] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 152));
		levelButtons[9] = new Button(Main.resourceLoader.levelUpButton,
				new Vector2(250, Main.GAME_HEIGHT / 2 + 172));

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

		// Draw level up animation

		if (player.getStats().getPoints() > 0) {
			if (!levelUpStart) {
				Main.resourceLoader.levelUpAnimation.start(false);
				levelUpStart = true;
			}
			Main.resourceLoader.levelUpAnimation
					.paint(g,
							new Vector2(
									10
											+ Main.resourceLoader.playerPortraits[playerPortrait]
													.getWidth()
											+ Main.resourceLoader.healthBar
													.getWidth() + 20,
									20
											+ Main.resourceLoader.playerPortraits[playerPortrait]
													.getHeight()
											/ 2
											+ Main.resourceLoader.healthBar
													.getHeight() / 2));
		} else {
			if (levelUpStart) {
				Main.resourceLoader.levelUpAnimation.stop();
				levelUpStart = false;
			}
		}

		// Draw Portrait

		g.drawImage(Main.resourceLoader.playerPortraits[playerPortrait], 10,
				20, null);

		if (showCharacterScreen) {
			g.drawImage(Main.resourceLoader.statScreen, 20, Main.GAME_HEIGHT
					/ 2 - Main.resourceLoader.statScreen.getHeight() / 2, null);

			// Left page

			g.setColor(Color.WHITE);
			g.drawString("Strength: " + player.getStats().strength, 80,
					Main.GAME_HEIGHT / 2);
			g.drawString("Constitution: " + player.getStats().constitution, 80,
					Main.GAME_HEIGHT / 2 + 20);
			g.drawString("Fortitude: " + player.getStats().fortitude, 80,
					Main.GAME_HEIGHT / 2 + 40);
			g.drawString("Reflexes: " + player.getStats().reflexes, 80,
					Main.GAME_HEIGHT / 2 + 60);
			g.drawString("Mind: " + player.getStats().mind, 80,
					Main.GAME_HEIGHT / 2 + 80);
			g.drawString("Presence: " + player.getStats().presence, 80,
					Main.GAME_HEIGHT / 2 + 100);
			g.drawString("Spirit: " + player.getStats().spirit, 80,
					Main.GAME_HEIGHT / 2 + 120);
			g.drawString("Sanity: " + player.getStats().sanity, 80,
					Main.GAME_HEIGHT / 2 + 140);
			g.drawString("Awareness: " + player.getStats().awareness, 80,
					Main.GAME_HEIGHT / 2 + 160);
			g.drawString("Luck: " + player.getStats().luck, 80,
					Main.GAME_HEIGHT / 2 + 180);

			// Left Side Buttons

			if (player.getStats().getPoints() > 0) {
				for (Button button : levelButtons) {
					button.paint(g);
				}
			}

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

		}

		if (showInventory) {
			player.getInventory().paint(g);
		}

		if (holdingItem) {
			g.drawImage(heldItem.getItemInvImage(), mouseRect.x, mouseRect.y,
					null);
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
						player.getEquipment()[0].giveItem(heldItem);
						heldItem = null;
						holdingItem = false;
					}
				} else if (player.getEquipment()[1].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[1]
							.getType()) {
						player.getEquipment()[1].giveItem(heldItem);
						heldItem = null;
						holdingItem = false;
					}
				} else if (player.getEquipment()[2].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[2]
							.getType()) {
						player.getEquipment()[2].giveItem(heldItem);
						heldItem = null;
						holdingItem = false;
					}
				} else if (player.getEquipment()[3].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[3]
							.getType()) {
						player.getEquipment()[3].giveItem(heldItem);
						heldItem = null;
						holdingItem = false;
					}
				} else if (player.getEquipment()[4].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[4]
							.getType()) {
						player.getEquipment()[4].giveItem(heldItem);
						heldItem = null;
						holdingItem = false;
					}
				} else if (player.getEquipment()[5].getRect().contains(
						mouseRect)) {
					if (heldItem.getStats().itemType == player.getEquipment()[5]
							.getType()) {
						player.getEquipment()[5].giveItem(heldItem);
						heldItem = null;
						holdingItem = false;
					}
				}
			}
		}

		if (showInventory) {
			for (Slot slot : player.getInventory().getSlots()) {
				if (slot.getRect().contains(mouseRect)) {
					if (slot.getItem() != null && !holdingItem) {
						holdingItem = true;
						heldItem = slot.getItem();
						slot.removeItem();
					} else if (holdingItem) {
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
		}
	}
}
