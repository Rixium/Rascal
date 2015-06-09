package com.bourneless.engine.math;

public class Random {

	private static java.util.Random random = new java.util.Random();

	public static int getRandom(int max) {
		return random.nextInt(max);
	}

}
