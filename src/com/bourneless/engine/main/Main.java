package com.bourneless.engine.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.bourneless.engine.input.InputListener;

public class Main {

	public final static String title = "Rascal"; // Game Title.

	public static final int SCALE = 2;
	public final static int GAME_WIDTH = 640 * SCALE; // Default Width.
	public static final int GAME_HEIGHT = 360 * SCALE; // Default Height.

	public static ResourceLoader resourceLoader;
	public static JFrame frame = new JFrame();
	public static Game game = new Game(frame, GAME_WIDTH, GAME_HEIGHT);

	private static InputListener inputListener;

	public static void main(String[] args) {

		inputListener = new InputListener();
		game.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		frame.setLayout(new BorderLayout());
		frame.add(game);
		resourceLoader = new ResourceLoader();
		frame.setTitle(title); // Set the title of JFrame.
		frame.setFocusable(true);
		frame.setResizable(false); // Stop the window getting resized.
		frame.addMouseListener(inputListener);
		frame.addKeyListener(inputListener);
		frame.addMouseMotionListener(inputListener);
		game.setFocusable(true);
		frame.pack(); // Correct the window size.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on JFrame close.
		frame.setLocationRelativeTo(null); // Make window appear in centre of screen.
		frame.setVisible(true); // Set the window to visible.
		game.passInsets(frame.getInsets());
		frame.createBufferStrategy(3);
		frame.setIconImage(Main.resourceLoader.icon);

	}
}
