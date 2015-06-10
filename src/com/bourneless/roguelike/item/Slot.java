package com.bourneless.roguelike.item;

import java.awt.Color;
import java.awt.Graphics2D;

import com.bourneless.engine.math.Vector2;

public class Slot {

	private Item item;
	private Vector2 pos;
	private int slotSize = 0;
	private boolean free = true;
	
	public Slot(Vector2 pos, int slotSize) {
		this.pos = pos;
		this.slotSize = slotSize;
	}
	
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawRect(pos.x, pos.y, slotSize, slotSize);
		if(item != null) {
			g.drawImage(item.getItemInvImage(), pos.x, pos.y, null);
		}
	}
	
	public void setItem(Item item) {
		this.item = item;
		free = false;
	}
	
	public void removeItem() {
		this.item = null;
		free = true;
	}
	
	public boolean isFree() {
		return this.free;
	}
	
	public Item getItem() {
		return this.item;
	}
	
}
