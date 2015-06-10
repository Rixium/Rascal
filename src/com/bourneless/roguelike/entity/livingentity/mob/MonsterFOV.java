package com.bourneless.roguelike.entity.livingentity.mob;

import com.bourneless.roguelike.entity.Entity;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.tile.TileClass;

public class MonsterFOV {

	private Map map;

	public void CheckFieldOfView(Map map, Player player, Mob mob) {
		float x, y;

		for (int i = 0; i < 360; i += 2) {
			x = (float) Math.cos(i * 0.01745f);
			y = (float) Math.sin(i * 0.01745f);
			DoFov(x, y, player, map, mob);
		}
	}

	private void DoFov(float x, float y, Player player, Map map, Mob mob) {
		int i;
		float ox, oy;

		ox = (float) mob.getTile().getTileX() + 0.5f;
		oy = (float) mob.getTile().getTileY() + 0.5f;

		for (i = 0; i < mob.getViewDistance(); i++) {
			if ((int) ox < map.getTiles().length
					&& (int) oy < map.getTiles().length && (int) ox > 0
					&& (int) oy > 0) {
				if (map.getTiles()[(int) ox][(int) oy].getTileClass() == TileClass.WALL) {
					return;
				}
				if (map.getTiles()[(int) ox][(int) oy].hasEntity()) {
					for (Entity entity : map.getTiles()[(int) ox][(int) oy]
							.getEntities()) {
						if (entity.getType() == EntityType.DOOR) {
							return;
						}
						if (entity.getType() == EntityType.PLAYER) {
							mob.setDestination(player.getTile());
							return;
						}
					}
				}

				ox += x;
				oy += y;
			}
		}
	}
}