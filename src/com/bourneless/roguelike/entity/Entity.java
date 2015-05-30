package com.bourneless.roguelike.entity;

import java.awt.image.BufferedImage;

import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.map.tile.Tile;

@SuppressWarnings("unused")
public class Entity{
	
	private Vector2 pos;
	private Tile tile;
	private BufferedImage image;
	protected int type;
	
	public Entity(Tile tile, BufferedImage image){
		this.pos = new Vector2(tile.getPos().x, tile.getPos().y);
		this.image = image;
		this.tile = tile;
	}
	
	public Tile getTile(){
		return this.tile;
	}
	
}
