package com.bourneless.roguelike.entity;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.map.Room;
import com.bourneless.roguelike.map.tile.Tile;
import com.bourneless.roguelike.map.tile.TileClass;

public class FieldOfView {

	private Room room;

	public FieldOfView(Room room) {
		this.room = room;
	}

	public void setVisibility(Entity entity, int xOffset, int yOffset) {
		for (int i = 0; i < room.getTiles().length; i++) {
			for (int j = 0; j < room.getTiles()[i].length; j++) {
				if (room.getTiles()[i][j].getPos().x < Main.GAME_WIDTH + -xOffset
						&& room.getTiles()[i][j].getPos().y < Main.GAME_HEIGHT + -yOffset
								+ (Tile.size * 3)
						&& room.getTiles()[i][j].getPos().x > -xOffset - Tile.size
						&& room.getTiles()[i][j].getPos().y > -yOffset - Tile.size) {
					if (entity.getTile().getTileY() > room.getTiles()[i][j]
							.getTileY()
							&& entity.getTile().getTileX() == room.getTiles()[i][j]
									.getTileX()) {
						if (room.getTiles()[i][j].getTileClass() == TileClass.WALL) {
							for (int k = 0; k < room.getTiles().length; k++) {
								for (int l = 0; l < room.getTiles()[j].length; l++) {
									if (l < j - 2) {
										room.getTiles()[k][l].setVisible(false);
									} else if (l > j - 2 && j < l - 2) {
										room.getTiles()[k][l].setVisible(true);
									}
								}
							}
						}
					} else if (entity.getTile().getTileY() < room.getTiles()[i][j]
							.getTileY()
							&& entity.getTile().getTileX() == room.getTiles()[i][j]
									.getTileX()) {
						if (room.getTiles()[i][j].getTileClass() == TileClass.WALL) {
							for (int k = 0; k < room.getTiles().length; k++) {
								for (int l = 0; l < room.getTiles()[j].length; l++) {
									if (j < l - 2) {
										room.getTiles()[k][l].setVisible(false);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
