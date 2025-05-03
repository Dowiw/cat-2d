package src.main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {

	JFrame window = new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.setTitle("Cats Meow");

	GamePanel gamePanel = new GamePanel();
	window.add(gamePanel);

	//Game Panel Viewer
	window.pack();

	window.setLocationRelativeTo(null);
	window.setVisible(true);

	//Game Panel starterw
	gamePanel.startGameThread();
	}
}
