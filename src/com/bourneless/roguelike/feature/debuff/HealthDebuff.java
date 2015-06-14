package com.bourneless.roguelike.feature.debuff;

import com.bourneless.engine.math.Random;
import com.bourneless.roguelike.entity.EntityType;
import com.bourneless.roguelike.entity.livingentity.LivingEntity;
import com.bourneless.roguelike.entity.livingentity.mob.Mob;
import com.bourneless.roguelike.entity.livingentity.player.Player;

public class HealthDebuff extends Debuff {

	public HealthDebuff(int count) {
		this.count = Random.getRandom(40);
	}

	public void tick(LivingEntity e) {
		if (count > 0) {
			if (e.getType() == EntityType.PLAYER) {
				Player player = (Player) e;
				player.hurt(player.getStats().constitution
						* player.getStats().baseConst
						- Random.getRandom(player.getStats().luck)
						* Random.getRandom(player.getStats().fortitude));
			} else if (e.getType() == EntityType.ENEMY) {
				Mob mob = (Mob) e;
				mob.hurt(mob.getStats().constitution);
			}
			count--;
		}
	}
}
