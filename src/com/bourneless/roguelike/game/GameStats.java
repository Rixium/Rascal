package com.bourneless.roguelike.game;

import java.io.Serializable;

public class GameStats implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int totalDeaths = 0;
	public int totalKills = 0;
	public int goldLooted = 0;

}
