package com.bourneless.roguelike.entity;

import com.bourneless.roguelike.map.Room;
import com.bourneless.roguelike.map.tile.TileClass;

public class FieldOfView {

	private Room room;

	public FieldOfView(Room room) {
		this.room = room;
	}
	
	public void setVisibility(Entity entity) {
		for(int i = 0; i < room.getTiles().length; i++) {
			for(int j = 0; j < room.getTiles()[i].length; j++) {
				if(entity.getTile().getTileY() > room.getTiles()[i][j].getTileY() && entity.getTile().getTileX() == room.getTiles()[i][j].getTileX()) {
					if(room.getTiles()[i][j].getTileClass() == TileClass.WALL) {
						for(int k = 0; k < room.getTiles().length; k++) {
							for(int l = 0; l < room.getTiles()[j].length; l++) {
								if(l < j - 2) {
									room.getTiles()[k][l].setVisible(false);
								} else {
									room.getTiles()[k][l].setVisible(true);
								}
							}
						}
					}
				}
			}
		}
	}
}
