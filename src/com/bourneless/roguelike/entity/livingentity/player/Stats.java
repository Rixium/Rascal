package com.bourneless.roguelike.entity.livingentity.player;

import java.util.Random;

import com.bourneless.engine.main.Main;

public class Stats {

	private Random random = new Random();
	private String name;
	private String speciality;
	private String title;

	private int level = 1;
	private int experience = 0;
	private int expToLevel = 100;

	private int strength = 10;

	public Stats() {
		createPlayer();
	}

	public void addExperience(int exp) {
		if (this.experience + exp >= expToLevel) {
			this.level++;
			this.experience += exp;
			this.experience -= expToLevel;
			expToLevel = 100 * level;
		} else {
			this.experience += exp;
		}
	}

	public int getExperience() {
		return this.experience;
	}

	public int getExpToLevel() {
		return this.expToLevel;
	}

	public int getStrength() {
		return this.strength;
	}

	public String getName() {
		return this.name;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public String getTitle() {
		return this.title;
	}

	public void createPlayer() {
		this.name = Main.resourceLoader.names.get(random
				.nextInt(Main.resourceLoader.names.size()));
		this.speciality = Main.resourceLoader.specialities.get(random
				.nextInt(Main.resourceLoader.specialities.size()));
		this.title = Main.resourceLoader.titles.get(random
				.nextInt(Main.resourceLoader.titles.size()));
	}
}
