package com.bourneless.roguelike.item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.bourneless.engine.math.Vector2;

public class Item {

	protected ItemStats stats;
	protected BufferedImage itemInvImage;
	
	public Item() {
		
	}
	
	public void drawButton(Vector2 pos, Graphics2D g) {
		g.drawImage(itemInvImage, pos.x, pos.y, null);
	}
	
	public void setItemInvImage(BufferedImage image) {
		this.itemInvImage = image;
	}
	
	public BufferedImage getItemInvImage() {
		return this.itemInvImage;
	}
	
	public ItemStats getStats() {
		return this.stats;
	}
	
}
