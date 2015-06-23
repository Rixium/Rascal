package com.bourneless.roguelike.screen;

import java.awt.Graphics2D;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.engine.util.Button;

public class LeaveFloorScreen {

	private Button yesButton;
	private Button noButton;

	public LeaveFloorScreen() {
		yesButton = new Button(Main.resourceLoader.yesButtonImage, new Vector2(
				Main.GAME_WIDTH / 2
						- Main.resourceLoader.yesButtonImage.getWidth() - 10,
				Main.GAME_HEIGHT / 2
						+ Main.resourceLoader.nextFloorScreen.getHeight() / 4));
		noButton = new Button(Main.resourceLoader.noButtonImage, new Vector2(
				Main.GAME_WIDTH / 2 + 10, Main.GAME_HEIGHT / 2
						+ Main.resourceLoader.nextFloorScreen.getHeight() / 4));
	}

	public void paint(Graphics2D g) {
		g.drawImage(
				Main.resourceLoader.nextFloorScreen,
				Main.GAME_WIDTH / 2
						- Main.resourceLoader.nextFloorScreen.getWidth() / 2,
				Main.GAME_HEIGHT / 2
						- Main.resourceLoader.nextFloorScreen.getHeight() / 2,
				null);

		yesButton.paint(g);
		noButton.paint(g);
	}

	public void update() {

	}

	public Button getYesButton() {
		return this.yesButton;
	}

	public Button getNoButton() {
		return this.noButton;
	}
}
