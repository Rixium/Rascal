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

	public int baseStrength = 2;
	public int strength = 2;

	public int baseConst = 2;
	public int constitution = 2;

	public int baseFort = 2;
	public int fortitude = 2;

	public int baseRefl = 2;
	public int reflexes = 2;

	public int baseMind = 2;
	public int mind = 2;

	public int basePres = 2;
	public int presence = 2;

	public int baseSpir = 2;
	public int spirit = 2;

	public int baseSan = 2;
	public int sanity = 2;

	public int baseAware = 2;
	public int awareness = 2;

	public int baseLuck = 2;
	public int luck = 2;

	private int points = 25;

	private Player player;

	public Stats(Player player) {
		this.player = player;
		createPlayer(player);
	}

	public void addExperience(int exp) {
		if (this.experience + exp >= expToLevel) {
			this.level++;
			Main.resourceLoader
					.playClip(Main.resourceLoader.levelUp, 1f, false);
			if (player.getHealth() + 100 <= player.getMaxHealth()) {
				player.addHealth(100);
			} else {
				player.setHealth(player.getMaxHealth());
			}

			this.experience += exp;
			this.experience -= expToLevel;
			expToLevel = 100 * level;
			points += 3;
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

	public int getPoints() {
		return this.points;
	}

	public void removePoint() {
		this.points -= 1;
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
				baseStrength += 1;
				points -= 1;
				break;
			case 1:
				baseConst += 1;
				points -= 1;
				break;
			case 2:
				baseFort += 1;
				points -= 1;
				break;
			case 3:
				baseRefl += 1;
				points -= 1;
				break;
			case 4:
				baseMind += 1;
				points -= 1;
				break;
			case 5:
				basePres += 1;
				points -= 1;
				break;
			case 6:
				baseSpir += 1;
				points -= 1;
				break;
			case 7:
				baseSan += 1;
				points -= 1;
				break;
			case 8:
				baseAware += 1;
				points -= 1;
				break;
			case 9:
				baseLuck += 1;
				points -= 1;
				break;
			default:
				break;
			}
		}
		checkStats(null);
		player.setHealth(player.getHealth() * constitution);
		points = 5;
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

	public void checkStats(EquipmentSlot[] e) {
		strength = baseStrength;
		fortitude = baseFort;
		constitution = baseConst;
		reflexes = baseRefl;
		luck = baseLuck;
		spirit = baseSpir;
		sanity = baseSan;
		awareness = baseAware;
		mind = baseMind;
		presence = basePres;

		if (e != null) {
			for (int i = 0; i < e.length; i++) {
				if (e[i].hasItem()) {
					strength += e[i].getItem().getStats().itemStrength;
					fortitude += e[i].getItem().getStats().itemFortitude;
					reflexes += e[i].getItem().getStats().itemReflexes;
					constitution += e[i].getItem().getStats().itemConstitution;
				}
			}
		}
	}

}
