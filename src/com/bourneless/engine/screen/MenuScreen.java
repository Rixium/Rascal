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

	public MenuScreen() {

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
