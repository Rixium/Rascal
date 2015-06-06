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
						- Main.resourceLoader.healthBar.getHeight(), player
						.getHealth() * 2, Main.resourceLoader.healthBar
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
	}
}
