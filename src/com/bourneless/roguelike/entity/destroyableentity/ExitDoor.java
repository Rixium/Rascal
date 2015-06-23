package com.bourneless.roguelike.entity.destroyableentity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.screen.GameScreen;

public class ExitDoor extends DestroyableEntity {

	public ExitDoor(Tile tile, BufferedImage exit) {
		super(tile, exit, EntityType.BREAKABLE);
		this.solid = true;
		this.name = "exit";
		this.broken = true;
	}

	public void paint(Graphics2D g) {
		g.drawImage(image, pos.x + xOffset, pos.y + yOffset, null);
	}

	public void hit(int h) {
		GameScreen screen = (GameScreen) Main.game.getScreen();
		screen.getInstance().goNextFloor();
	}
}
