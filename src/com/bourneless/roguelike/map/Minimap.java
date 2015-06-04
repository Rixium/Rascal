package com.bourneless.roguelike.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.map.tile.TileClass;

public class Minimap {

	private Map map;
	private BufferedImage image;
	private BufferedImage imageMap;
	private int xOffset;
	private int yOffset;
	private boolean showMap = false;
	private boolean hasDrawn = false;

	public Minimap(Map map) {
		this.map = map;

		imageMap = new BufferedImage(map.getTiles().length,
				map.getTiles().length, BufferedImage.TYPE_INT_RGB);
		image = new BufferedImage(imageMap.getWidth(), imageMap.getHeight(),
				BufferedImage.TYPE_INT_RGB);
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g) {
		if (showMap) {

			if (!hasDrawn) {
				Graphics2D g2d = imageMap.createGraphics();
				for (int i = 0; i < map.getTiles().length; i++) {
					for (int j = 0; j < map.getTiles()[i].length; j++) {
						if (map.getTiles()[i][j].getTileClass() == TileClass.FLOOR) {
							g2d.drawImage(Main.resourceLoader.tiles[map
									.getTiles()[i][j].getTileType()], map
									.getTiles()[i][j].getTileX(), map
									.getTiles()[i][j].getTileY(), 1, 1, null);
							if (map.getTiles()[i][j].hasEntity()) {
								for (int k = 0; k < map.getTiles()[i][j]
										.getEntities().size(); k++) {
									if (map.getTiles()[i][j].getEntities()
											.get(k).getType() == EntityType.PLAYER) {
										g2d.setColor(Color.GREEN);
									} else if (map.getTiles()[i][j]
											.getEntities().get(k).getType() == EntityType.ENEMY) {
										g2d.setColor(Color.RED);
									} else if (map.getTiles()[i][j]
											.getEntities().get(k).getType() == EntityType.BREAKABLE) {
										g2d.setColor(Color.GRAY);
									}
								}

								g2d.fillRect(map.getTiles()[i][j].getTileX(),
										map.getTiles()[i][j].getTileY(), 1, 1);
							}

						} else {
							g2d.setColor(Color.black);
							g2d.fillRect(map.getTiles()[i][j].getTileX(),
									map.getTiles()[i][j].getTileY(), 1, 1);
						}
					}
				}
				hasDrawn = true;
			}

			g.drawImage(imageMap, Main.GAME_WIDTH / 2 - image.getWidth(),
					Main.GAME_HEIGHT / 2 - image.getHeight(),
					image.getWidth() * 2, image.getHeight() * 2, null);
		}
	}

	public void showMap(boolean bool) {
		this.showMap = bool;
	}

	public boolean getShowing() {
		return this.showMap;
	}

	public void setDrawn(boolean bool) {
		this.hasDrawn = bool;
	}
}
