package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.item.ItemDropper;
import com.bourneless.roguelike.map.tile.Tile;

public class Chest extends DestroyableEntity {

	private boolean hasDropped = false;
	private ItemDropper iDropper = new ItemDropper();

	public Chest(Tile tile, BufferedImage image, int type) {
		super(tile, image, EntityType.BREAKABLE);
		this.name = "chest";
		this.solid = true;
		this.passable = false;
		tile.setPassable(false);
	}

	public void paint(Graphics2D g) {
		if (this.broken) {
			g.drawImage(Main.resourceLoader.chest[1], pos.x + xOffset, pos.y
					+ yOffset, null);
		} else {
			g.drawImage(Main.resourceLoader.chest[0], pos.x + xOffset, pos.y
					+ yOffset, null);
		}
	}

	public void dropItem(Player player) {
		if (!hasDropped) {
			iDropper.dropItem(player);
			hasDropped = true;
		}
	}

	public boolean getBroken() {
		return this.broken;
	}

	public boolean givenItem() {
		return this.hasDropped;
	}

}
