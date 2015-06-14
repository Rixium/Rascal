package com.bourneless.roguelike.feature.debuff;

import com.bourneless.roguelike.entity.livingentity.LivingEntity;

public class Debuff {

	protected int count = 0;

	public void tick(LivingEntity e) {
		if (count > 0) {
			count--;
		}
	}

	public boolean hasEnded() {
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}
}
