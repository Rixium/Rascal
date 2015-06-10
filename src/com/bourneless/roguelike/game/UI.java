package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.livingentity.player.Player;

public class UI {

	private Random random = new Random();
	private Player player;
	private Font font;

	private int playerPortrait;
	private boolean showCharacterScreen = false;

	public UI(Player player) {
		font = new Font("A Font With Serifs", Font.PLAIN, 18);
		this.player = player;
		playerPortrait = random
				.nextInt(Main.resourceLoader.playerPortraits.length);
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

			int xPos = Main.GAME_HEIGHT / 2 - stringHeight / 2;

			g.drawString(nameString, start, xPos);

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
}
