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
				if (map.getTiles()[i][j] != null) {
					map.getTiles()[i][j].setVisible(false);
				}
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
			if ((int) ox < map.getTiles().length
					&& (int) oy < map.getTiles().length && (int) ox > 0
					&& (int) oy > 0) {
				map.getTiles()[(int) ox][(int) oy].setVisible(true);
				map.getTiles()[(int) ox][(int) oy].setSeen();

				if (map.getTiles()[(int) ox][(int) oy].getTileClass() == TileClass.WALL) {
					return;
				}

				if (map.getTiles()[(int) ox][(int) oy].hasEntity()) {
					for (Entity entity : map.getTiles()[(int) ox][(int) oy]
							.getEntities()) {
						if (entity.getType() == EntityType.BREAKABLE
								&& !entity.getPassable()) {
							if (!entity.getName().matches("exit")
									&& !entity.getName().matches("chest")) {
								return;
							}
						}
					}
				}
				ox += x;
				oy += y;
			}
		}
	}
}
