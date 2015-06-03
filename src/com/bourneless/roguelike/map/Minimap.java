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
	private int xOffset;
	private int yOffset;

	public Minimap(Map map) {
		this.map = map;
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	}

	public void update(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void paint(Graphics2D g) {
		Graphics2D g2d = image.createGraphics();
		for (int i = 0; i < map.getTiles().length; i++) {
			for (int j = 0; j < map.getTiles()[i].length; j++) {
				if (map.getTiles()[i][j].getTileClass() == TileClass.FLOOR
						&& map.getTiles()[i][j].getSeen()) {
					g2d.drawImage(
							Main.resourceLoader.tiles[map.getTiles()[i][j]
									.getTileType()],
							map.getTiles()[i][j].getTileX() + i + xOffset / 64,
							map.getTiles()[i][j].getTileY() + j + yOffset / 64,
							64, 64, null);
					if (map.getTiles()[i][j].hasEntity()) {
						for(int k = 0; k < map.getTiles()[i][j].getEntities().size(); k++) {
							if(map.getTiles()[i][j].getEntities().get(k).getType() == EntityType.PLAYER) {
								g2d.setColor(Color.GREEN);
							} else if (map.getTiles()[i][j].getEntities().get(k).getType() == EntityType.ENEMY) {
								g2d.setColor(Color.RED);
							} else if (map.getTiles()[i][j].getEntities().get(k).getType() == EntityType.BREAKABLE) {
								g2d.setColor(Color.GRAY);
							}
						}
						
						g2d.fillRect(map.getTiles()[i][j].getTileX() + i + xOffset
								/ 64, map.getTiles()[i][j].getTileY() + j + yOffset
								/ 64, 64, 64);
					}

				} else {
					g2d.setColor(Color.black);
					g2d.fillRect(map.getTiles()[i][j].getTileX() + i + xOffset
							/ 64, map.getTiles()[i][j].getTileY() + j + yOffset
							/ 64, 64, 64);
				}
			}
		}
		g2d.setColor(Color.red);
		g2d.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
		g.drawImage(image, 10, 10, image.getWidth(), image.getHeight(), null);
	}
}
