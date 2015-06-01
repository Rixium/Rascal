package com.bourneless.roguelike.entity;

import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.Room;
import com.bourneless.roguelike.map.tile.TileClass;
import com.bourneless.roguelike.map.tile.WallTileType;

public class FieldOfView {

	private Room room;

	public void CheckFieldOfView(Room room, Player player) {
		float x, y;

		for (int i = 0; i < room.getTiles().length; i++) {
			for (int j = 0; j < room.getTiles()[i].length; j++) {
				room.getTiles()[i][j].setVisible(false);
			}
		}

		for (int i = 0; i < 360; i += 2) {
			x = (float) Math.cos(i * 0.01745f);
			y = (float) Math.sin(i * 0.01745f);
			DoFov(x, y, room, player);
		}
	}

	private void DoFov(float x, float y, Room room, Player player) {
		int i;
		float ox, oy;

		ox = (float) player.getTile().getTileX() + 0.5f;
		oy = (float) player.getTile().getTileY() + 0.5f;

		for (i = 0; i < player.getViewDistance(); i++) {
			room.getTiles()[(int) ox][(int) oy].setVisible(true);
			room.getTiles()[(int) ox][(int) oy].setSeen();

			if (room.getTiles()[(int) ox][(int) oy].getTileClass() == TileClass.WALL) {
				room.getTiles()[(int) ox][(int) oy - 1].setVisible(true);
				room.getTiles()[(int) ox][(int) oy - 2].setVisible(true);
				room.getTiles()[(int) ox][(int) oy - 1].setSeen();
				room.getTiles()[(int) ox][(int) oy - 2].setSeen();
				return;
			}
			ox += x;
			oy += y;
		}
	}
}
