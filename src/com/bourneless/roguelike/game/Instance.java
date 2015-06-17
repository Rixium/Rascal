package com.bourneless.roguelike.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

	private int floor = 1;
	private Map map;
	private Minimap miniMap;
	private UI ui;
	private Cheat cheat;
	private boolean playerTurn = true;
	
	private Rectangle mouseRect;

	private Font cheatFont = new Font("A Font With Serifs", Font.TRUETYPE_FONT,
			25);

	private Player player;

	private int xOffset = 0;
	private int yOffset = 0;

	private boolean ready = false;

	private int camSpeed = 4;
	private boolean deathScreenActive = false;
	private DeathScreen ds;

	private ArrayList<Hit> hits = new ArrayList<Hit>();

	public Instance() {
		map = new Map(this);

		map.generate();
		player = map.getPlayer();
		miniMap = new Minimap(map);
		xOffset = Main.GAME_WIDTH / 2 - player.getPos().x;
		yOffset = Main.GAME_HEIGHT / 2 - player.getPos().y;
		player.setTile(player.getTile());
		ui = new UI(player);

		cheat = new Cheat(player, this);

		for (int i = 0; i < map.getEntityList().size(); i++) {
			map.getEntityList().get(i)
					.setLayer(map.getEntityList().get(i).getTile().getTileY());
		}

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
			if (ds != null) {
				ds.paint(g);
			}
		}

		if (cheat.hasStarted()) {
			g.setColor(Color.white);
			g.setFont(cheatFont);
			String cheatString = "> " + cheat.cheat;
			int stringLength = (int) g.getFontMetrics()
					.getStringBounds(cheatString, g).getWidth();
			int stringHeight = (int) g.getFontMetrics()
					.getStringBounds(cheatString, g).getHeight();
			int start = Main.GAME_WIDTH - stringLength - 10;
			int xPos = 0 + stringHeight + 10;
			g.drawString(cheatString, start, xPos);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (!cheat.hasStarted()) {
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

		if (e.getKeyCode() == 16) {
			if (cheat.hasStarted()) {
				cheat.stop();
			} else {
				cheat.start();
			}
		}

		if (cheat.hasStarted()) {
			cheat.addKey(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (!cheat.hasStarted()) {
			if (!deathScreenActive) {
				player.keyReleased(e);
			}
		}
	}

	public void mousePressed(Rectangle rect) {
		this.mouseRect = rect;
		ui.mousePressed(rect);
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

	public void setShowDeathScreen(boolean bool) {
		this.deathScreenActive = bool;
		ds = null;
	}

	public void mouseMoved(Rectangle mouseRect) {
		ui.mouseMoved(mouseRect);
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getFloor() {
		return this.floor;
	}

	public Map getMap() {
		return this.map;
	}

	public Cheat getCheat() {
		return this.cheat;
	}
	
	public Rectangle getMouseRect() {
		return this.mouseRect;
	}
	
	public UI getUI() {
		return this.ui;
	}

}
