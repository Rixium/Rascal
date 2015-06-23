package com.bourneless.roguelike.entity.livingentity.mob;

import java.util.Random;

import org.json.simple.JSONObject;

import com.bourneless.engine.main.Main;
import com.bourneless.roguelike.game.Instance;
import com.bourneless.roguelike.screen.GameScreen;

public class MonsterStats {

	private Random random = new Random();
	public String name;

	public int level = 1;

	public int experienceWorth = 0;
	public int health = 0;
	public int maxHealth;

	public int strength = 0;
	public int constitution = 0;
	public int fortitude = 0;
	public int reflexes = 0;
	public int mind = 0;
	public int presence = 0;
	public int spirit = 0;
	public int sanity = 0;
	public int awareness = 0;
	public int luck = 0;

	public int defence = 0;

	public int rarity = 0;

	public int image = 0;

	public MonsterStats(Instance instance) {
		int rarity = random.nextInt(5501);

		if (rarity < 1000) {
			rarity = 1;
		} else if (rarity >= 1000 && rarity < 1900) {
			rarity = 2;
		} else if (rarity >= 1900 && rarity < 2700) {
			rarity = 3;
		} else if (rarity >= 2700 && rarity < 3400) {
			rarity = 4;
		} else if (rarity >= 3400 && rarity < 4000) {
			rarity = 5;
		} else if (rarity >= 4000 && rarity < 4500) {
			rarity = 6;
		} else if (rarity >= 4500 && rarity < 4900) {
			rarity = 7;
		} else if (rarity >= 4900 && rarity < 5200) {
			rarity = 8;
		} else if (rarity >= 5200 && rarity < 5400) {
			rarity = 9;
		} else if (rarity >= 5400 && rarity < 5500) {
			rarity = 10;
		} else if (rarity == 5500) {
			rarity = 11;
		}

		int getMonster = random.nextInt(Main.resourceLoader.monsters.size());
		JSONObject monster = (JSONObject) Main.resourceLoader.monsters
				.get(getMonster);
		while (((Long) monster.get("rarity")).intValue() != rarity) {
			getMonster = random.nextInt(Main.resourceLoader.monsters.size());
			monster = (JSONObject) Main.resourceLoader.monsters.get(getMonster);
		}

		createMonster(monster, instance);
	}

	public void createMonster(JSONObject thisMonster, Instance instance) {
		this.name = (String) thisMonster.get("name");

		this.strength = ((Long) thisMonster.get("strength")).intValue()
				* instance.getFloor();
		this.reflexes = ((Long) thisMonster.get("reflexes")).intValue()
				* instance.getFloor();
		this.fortitude = ((Long) thisMonster.get("fortitude")).intValue()
				* instance.getFloor();
		this.luck = ((Long) thisMonster.get("luck")).intValue()
				* instance.getFloor();

		this.image = ((Long) thisMonster.get("image")).intValue();

		this.experienceWorth = ((Long) thisMonster.get("experience_worth"))
				.intValue() * instance.getFloor();

		this.health = ((Long) thisMonster.get("health")).intValue()
				* instance.getFloor();
		this.maxHealth = health;

	}

}
