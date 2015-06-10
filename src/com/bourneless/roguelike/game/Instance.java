package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.bourneless.engine.main.Main;
import com.bourneless.engine.screen.MenuScreen;
import com.bourneless.roguelike.entity.Hit;
import com.bourneless.roguelike.entity.livingentity.player.Player;
import com.bourneless.roguelike.map.Map;
import com.bourneless.roguelike.map.Minimap;
import com.bourneless.roguelike.screen.DeathScreen;

public class Instance {

	private Map map;
	private Minimap miniMap;
	private UI ui;
	private boolean playerTurn = true;

	private Player player;

	private int xOffset = 0;
	private int yOffset = 0;

	private boolean ready = false;

	private int camSpeed = 4;
	private boolean deathScreenActive = false;
	private DeathScreen ds;

	private ArrayList<Hit> hits = new ArrayList<Hit>();

	public Instance() {
		map = new Map();

		map.generate();
		player = map.getPlayer();
		miniMap = new Minimap(map);
		xOffset = Main.GAME_WIDTH / 2 - player.getPos().x;
		yOffset = Main.GAME_HEIGHT / 2 - player.getPos().y;
		player.setTile(player.getTile());
		ui = new UI(player);

		ready = true;
	}

	public void update() {
		if (!deathScreenActive) {
			for (Hit hit : hits) {
				if (hit.getDead()) {
					hits.remove(hit);
					break;
				}
				hit.update(xOffset, yOffset);
			}

			if (Main.GAME_WIDTH / 2 - player.getPos().x - player.getXOff() < xOffset) {
				xOffset -= camSpeed;
			} else if (Main.GAME_WIDTH / 2 - player.getPos().x
					- player.getXOff() > xOffset) {
				xOffset += camSpeed;
			}
			if (Main.GAME_HEIGHT / 2 - player.getPos().y - player.getYOff() < yOffset) {
				yOffset -= camSpeed;
			} else if (Main.GAME_HEIGHT / 2 - player.getPos().y
					- player.getYOff() > yOffset) {
				yOffset += camSpeed;
			}

			map.update(xOffset, yOffset, this);

			if (playerTurn) {
				player.update(xOffset, yOffset, map, this);
			}

			miniMap.update(xOffset, yOffset);
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
		map.paint(g);

		try {
			for (Hit hit : hits) {
				hit.paint(g);
				if (hit.getDead()) {
					hits.remove(hit);
					break;
				}
			}
		} catch (ConcurrentModificationException e) {
			System.out.println("Hit break.");
		}

		miniMap.paint(g);
		
		ui.paint(g);
		

		if (deathScreenActive) {
			ds.paint(g);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (!deathScreenActive) {
			if (e.getKeyCode() == 32) {
				if (playerTurn) {
					playerTurn = false;
				}
			}
			if (e.getKeyCode() == 77) {
				miniMap.showMap(!miniMap.getShowing());
			} else {
				miniMap.setDrawn(false);
				miniMap.showMap(false);
				player.keyPressed(e, map);
			}

			if (e.getKeyCode() == 67) {
				miniMap.setDrawn(false);
				miniMap.showMap(false);
				player.keyPressed(e, map);
				ui.showCScreen(!ui.getShowingCScreen());
			} else if (e.getKeyCode() == 73) {
				miniMap.setDrawn(false);
				miniMap.showMap(false);
				player.keyPressed(e, map);
				ui.showInventoryScreen(!ui.getShowingInventoryScreen());
			}
		} else if (deathScreenActive) {
			if (e.getKeyCode() == 27) {
				Main.game.setScreen(new MenuScreen());
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (!deathScreenActive) {
			player.keyReleased(e);
		}
	}

	public boolean isReady() {
		return this.ready;
	}

	public boolean getPlayerTurn() {
		return this.playerTurn;
	}

	public void setPlayerTurn(boolean bool) {
		this.playerTurn = bool;
	}

	public ArrayList<Hit> getHits() {
		return this.hits;
	}

	public void showDeathScreen() {
		this.deathScreenActive = true;
		ds = new DeathScreen(player);
	}
}
