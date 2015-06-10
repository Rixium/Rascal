package com.bourneless.roguelike.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.entity.livingentity.player.Player;

public class DeathScreen {

	private Player player;
	private Font font = new Font("A Font With Serifs", Font.PLAIN, 40);

	Timer posTimer = new Timer(1, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (pos.y > endPos.y) {
				pos.y--;
			}
		}
	});

	private Vector2 pos = new Vector2(Main.GAME_WIDTH / 2
			- Main.resourceLoader.deathScreen.getWidth() / 2, Main.GAME_HEIGHT
			+ Main.resourceLoader.deathScreen.getHeight());
	private Vector2 endPos = new Vector2(Main.GAME_WIDTH / 2
			- Main.resourceLoader.deathScreen.getWidth() / 2, Main.GAME_HEIGHT
			/ 2 - Main.resourceLoader.deathScreen.getHeight() / 2);

	public DeathScreen(Player player) {
		this.player = player;
		posTimer.start();
	}

	public void update() {

	}

	public void paint(Graphics2D g) {
		g.drawImage(Main.resourceLoader.deathScreen, pos.x, pos.y, null);

		if (pos.y == endPos.y) {
			String s = "You Died";
			g.setFont(font);
			g.setColor(Color.WHITE);
			int stringLength = (int) g.getFontMetrics().getStringBounds(s, g)
					.getWidth();
			int stringHeight = (int) g.getFontMetrics().getStringBounds(s, g)
					.getHeight();
			int start = Main.GAME_WIDTH / 2 - stringLength / 2;
			int xPos = Main.GAME_HEIGHT / 2
					+ Main.resourceLoader.deathScreen.getHeight();

			g.drawString(s, start, xPos);
		}
	}

}
