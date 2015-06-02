package com.bourneless.roguelike.entity;

import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.Room;
import com.bourneless.roguelike.map.tile.TileClass;

public class FieldOfView {

	private Map map;
	
	public void CheckFieldOfView(Map map, Player player) {
		float x, y;
		for (int i = 0; i < map.getTiles().length; i++) {
			for (int j = 0; j < map.getTiles()[i].length; j++) {
				map.getTiles()[i][j].setVisible(false);
			}
		}

		for (int i = 0; i < 360; i += 2) {
			x = (float) Math.cos(i * 0.01745f);
			y = (float) Math.sin(i * 0.01745f);
			DoFov(x, y, player, map);
		}
	}

	private void DoFov(float x, float y, Player player, Map map) {
		int i;
		float ox, oy;

		ox = (float) player.getTile().getTileX() + 0.5f;
		oy = (float) player.getTile().getTileY() + 0.5f;

		for (i = 0; i < player.getViewDistance(); i++) {
			if(oy < 2) {
				oy = 2;
			} else if (oy > map.getTiles()[0].length) {
				oy = map.getTiles()[0].length;
			}
			
			map.getTiles()[(int) ox][(int) oy].setVisible(true);
			map.getTiles()[(int) ox][(int) oy].setSeen();

			if (map.getTiles()[(int) ox][(int) oy].getTileClass() == TileClass.WALL) {
				map.getTiles()[(int) ox][(int) oy - 1].setVisible(true);
				map.getTiles()[(int) ox][(int) oy - 2].setVisible(true);
				map.getTiles()[(int) ox][(int) oy - 1].setSeen();
				map.getTiles()[(int) ox][(int) oy - 2].setSeen();
				return;
			}
			ox += x;
			oy += y;
		}
	}
}
