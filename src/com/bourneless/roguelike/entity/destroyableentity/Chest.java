package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.math.Vector2;
import com.bourneless.engine.util.Button;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.Hit;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.screen.GameScreen;

public class Chest extends DestroyableEntity {

	private boolean hasDropped = false;
	private ChestContents contents;
	private boolean spawnedItems = false;
	private boolean showChest = false;

	private Button closeButton = new Button(Main.resourceLoader.closeButton,
			new Vector2(20 + 32 * 5 / 2 + Main.GAME_WIDTH / 2, Main.GAME_HEIGHT
					/ 2 - Main.resourceLoader.inventoryScreen.getHeight() / 2
					+ 10 - 60));

	public Chest(Tile tile, BufferedImage image, int type) {
		super(tile, image, EntityType.BREAKABLE);
		this.name = "chest";
		this.solid = false;
		this.passable = false;
		tile.setPassable(false);

	}

	public void paint(Graphics2D g) {
		if (this.broken) {
			if (!spawnedItems) {
				contents = new ChestContents();
				spawnedItems = true;
			}

			if (showChest) {
				contents.paint(g);
				closeButton.paint(g);
			}

			g.drawImage(Main.resourceLoader.chest[1], pos.x + xOffset, pos.y
					+ yOffset, null);

		} else {
			g.drawImage(Main.resourceLoader.chest[0], pos.x + xOffset, pos.y
					+ yOffset, null);
		}
	}

	public boolean getBroken() {
		return this.broken;
	}

	public boolean givenItem() {
		return this.hasDropped;
	}

	public boolean getShowChest() {
		return this.showChest;
	}

	public void closeChest() {
		this.showChest = false;
	}

	public void openChest() {
		this.showChest = true;

		GameScreen screen = (GameScreen) Main.game.getScreen();

		screen.getInstance().getUI().setChest(this);
	}

	public ChestContents getContents() {
		return this.contents;
	}

	public Button getCloseButton() {
		return this.closeButton;
	}

	public void hit(int h) {
		if (!broken) {
			this.health -= h;

			instance.getHits()
					.add(new Hit(h, new Vector2(pos.x + image.getWidth() / 2,
							pos.y)));

			if (h > 0) {
				Main.resourceLoader.playClip(Main.resourceLoader.hitSound, 1f,
						false);
			}
			if (health <= 0) {
				Main.resourceLoader.playClip(Main.resourceLoader.breakSound,
						1f, false);
				broken = true;
			}
		} else {
			if (!showChest) {
				openChest();
			} else {
				closeChest();
			}
		}
	}
}
