package com.bourneless.roguelike.entity.livingentity.player;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.bourneless.engine.math.Vector2;
import com.bourneless.roguelike.item.Item;

public class EquipmentSlot {

	private Item item;
	private int type;

	private Player player;

	private Vector2 pos;
	private Rectangle rect;

	private int slotSize = 32;

	public EquipmentSlot(int type, Player player) {
		this.type = type;
		this.player = player;
	}

	public Item getItem() {
		return item;
	}

	public void giveItem(Item item) {
		this.item = item;
	}

	public boolean hasItem() {
		if (item == null) {
			return false;
		} else {
			return true;
		}
	}

	public void paint(Graphics2D g) {
		g.fillRect(pos.x, pos.y, slotSize, slotSize);
		if (item != null) {
			item.drawButton(pos, g);
		}
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
		this.rect = new Rectangle(pos.x, pos.y, slotSize, slotSize);
	}

	public Rectangle getRect() {
		return this.rect;
	}

	public int getType() {
		return this.type;
	}

	public void removeItem() {
		this.item = null;
	}
}
