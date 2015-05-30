package com.bourneless.engine.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

import com.bourneless.engine.main.Main;

public class MenuScreen extends Screen {

	BufferedImage map;
	byte[] mapImage;

	private int renderX = 0;
	private int renderY = 0;

	public MenuScreen() {
		map = Main.resourceLoader.map;
		mapImage = ((DataBufferByte) map.getRaster().getDataBuffer()).getData();

		createMap();
	}

	public void createMap() {
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				int c = map.getRGB(i, j);
				Color color = new Color(c);
				String hex = "#"
						+ Integer.toHexString(color.getRGB()).substring(2);
				if (hex.matches("#" + "1800ff")) {

				} else if (hex.matches("#" + "ff0202")) {

				}
			}
		}
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
	}

	public void update() {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		Rectangle mouseRect = new Rectangle(e.getX(), e.getY() - 20, 10, 10);

	}

	public void loadGame() {

	}

}
