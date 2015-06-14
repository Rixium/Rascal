package com.bourneless.engine.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.bourneless.engine.screen.Screen;
import com.bourneless.engine.screen.SplashScreen;
import com.bourneless.roguelike.game.GameStats;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean running = false;

	private JFrame frame;

	private Thread thread;
	private int frames = 0;
	private int updates = 0;
	public static int width;
	public static int height;

	// Insets

	private int top, left, bottom, right;

	private BufferedImage image;
	private Image scaledImage;

	private Screen currentScreen;

	private int resolution;

	private GameStats gameStats = new GameStats();

	private float durationMS = 0;

	private boolean loaded = false;
	private long loadPerc = 0;
	private boolean loading = false;
	private Font loadingFont = new Font("A Font With Serifs", Font.PLAIN, 50);

	@SuppressWarnings("static-access")
	public Game(JFrame frame, int width, int height) {

		this.setDoubleBuffered(true);
		this.width = width;
		this.height = height;
		this.frame = frame;
		this.setMaximumSize(new Dimension(Main.GAME_WIDTH, Main.GAME_HEIGHT));
		this.setMinimumSize(new Dimension(640, 360));

		initialize();
	}

	public void initialize() {
		System.out.println("Initializing..");

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		loaded = true;
		currentScreen = new SplashScreen();

		running = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	public void update() {
		if (loaded) {
			currentScreen.update();
		}

	}

	public void blit() {
		float current = System.currentTimeMillis();
		Graphics2D g2d = (Graphics2D) image.createGraphics(); // Create graphics of g2d.
		g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
		if (loaded) {
			currentScreen.paint(g2d);
		} else {
			g2d.setColor(new Color(2433814));
			g2d.fillRect(0, 0, Main.GAME_WIDTH, Main.GAME_HEIGHT);
			g2d.setColor(new Color(16776164));
			g2d.setFont(loadingFont);
			int stringLength = (int) g2d.getFontMetrics()
					.getStringBounds("Loading", g2d).getWidth();
			int stringHeight = (int) g2d.getFontMetrics()
					.getStringBounds("Loading", g2d).getHeight();
			int start = Main.GAME_WIDTH / 2 - stringLength / 2;
			int xPos = Main.GAME_HEIGHT / 2 - stringHeight / 2;
			g2d.drawString("Loading", start, xPos);

			stringLength = (int) g2d.getFontMetrics()
					.getStringBounds(loadPerc + "%", g2d).getWidth();
			stringHeight = (int) g2d.getFontMetrics()
					.getStringBounds(loadPerc + "%", g2d).getHeight();
			start = Main.GAME_WIDTH / 2 - stringLength / 2;
			xPos = Main.GAME_HEIGHT / 2 + stringHeight / 2;
			g2d.drawString(loadPerc + "%", start, xPos);
		}
		g2d.dispose();
	}

	public void draw() {
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null && frame.isVisible()) {
			frame.createBufferStrategy(3);
			return;
		}
		if (bs != null) {
			Graphics g = bs.getDrawGraphics();
			blit();
			g.drawImage(image, left, top, null);
			g.dispose();
			bs.show();
			super.paintComponent(g);
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000 / 60D;
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {

				update();

				updates++;
				delta--;
			}
			draw();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(Main.title + " | FPS: " + frames + " | UPS: "
						+ updates);
				updates = 0;
				frames = 0;
			}

			try {
				thread.sleep(0);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.exit(0);
	}

	public Screen getScreen() {
		if (loaded) {
			return this.currentScreen;
		}
		return null;
	}

	public void setScreen(Screen screen) {
		if (loaded) {
			this.currentScreen = screen;
			System.gc();
		}
	}

	public void passInsets(Insets insets) {
		left = insets.left;
		right = insets.right;
		top = insets.top;
		bottom = insets.bottom;
	}

	public boolean getLoaded() {
		return loaded;
	}

	public GameStats getGameStats() {
		return this.gameStats;
	}

}
