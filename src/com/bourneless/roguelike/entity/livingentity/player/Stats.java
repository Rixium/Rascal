package com.bourneless.roguelike.entity.livingentity.player;

import java.util.Random;

import com.bourneless.engine.main.Main;

public class Stats {

	private Random random = new Random();
	private String name;
	private String speciality;
	private String title;

	public int level = 1;
	public int experience = 0;
	public int expToLevel = 100;

	public int strength = 5;
	public int constitution = 5;
	public int fortitude = 5;
	public int reflexes = 5;
	public int mind = 5;
	public int presence = 5;
	public int spirit = 5;
	public int sanity = 5;
	public int awareness = 5;
	public int luck = 5;

	private int points = 10;

	public Stats(Player player) {
		createPlayer(player);
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

	public void createPlayer(Player player) {
		this.name = Main.resourceLoader.names.get(random
				.nextInt(Main.resourceLoader.names.size()));
		this.speciality = Main.resourceLoader.specialities.get(random
				.nextInt(Main.resourceLoader.specialities.size()));
		this.title = Main.resourceLoader.titles.get(random
				.nextInt(Main.resourceLoader.titles.size()));

		while (points > 0) {
			int skill = random.nextInt(10);

			switch (skill) {
			case 0:
				strength += 1;
				points -= 1;
				break;
			case 1:
				constitution += 1;
				points -= 1;
				break;
			case 2:
				fortitude += 1;
				points -= 1;
				break;
			case 3:
				reflexes += 1;
				points -= 1;
				break;
			case 4:
				mind += 1;
				points -= 1;
				break;
			case 5:
				presence += 1;
				points -= 1;
				break;
			case 6:
				spirit += 1;
				points -= 1;
				break;
			case 7:
				sanity += 1;
				points -= 1;
				break;
			case 8:
				awareness += 1;
				points -= 1;
				break;
			case 9:
				luck += 1;
				points -= 1;
				break;
			default:
				break;
			}
		}
		player.setHealth(player.getHealth() * constitution);
		print();
	}

	private void print() {
		System.out.println();
		System.out.println("Character Created..");
		System.out.println("______________");
		System.out.println();
		System.out.println("Name: " + name + " the " + title + " of "
				+ speciality + "!");
		System.out.println("Strength: " + strength);
		System.out.println("Constitution: " + constitution);
		System.out.println("Fortitude: " + fortitude);
		System.out.println("Reflexes: " + reflexes);
		System.out.println("Mind: " + mind);
		System.out.println("Presence: " + presence);
		System.out.println("Spirit: " + spirit);
		System.out.println("Sanity: " + sanity);
		System.out.println("Awareness: " + awareness);
		System.out.println("Luck: " + luck);
		System.out.println("______________");
		System.out.println();
	}
}
