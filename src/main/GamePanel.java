package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import src.entity.Player;
import src.tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	//Screen settings
	final int originalTileSize = 16; //Default Size for a tile (pixel based)
	final int scale = 3; //Pixel Scaler

	public final int tileSize = originalTileSize * scale; //Default Size for a scaled tile

	//Screen size
	public final int maxScreenCol = 16; //Default 16
	public final int maxScreenRow = 12; //Default 12
	public final int screenWidth = tileSize * maxScreenCol; //Width of Panel
	public final int screenHeight = tileSize * maxScreenRow; //Height of Panel

	//Main World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	//FPS
	int FPS = 60;

	//Runners
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker (this);
	public Player player = new Player(this, keyH);
	public Exception gp;

	//Game Panel constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //Thread
		this.addKeyListener(keyH); //Keys
		this.setFocusable(true);
	}

	//Thread to start
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	//Game Loop
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				update(); //Call updater
				repaint(); //Call paintComponent
				delta--;
			}
		}
	}

	//Update Method for Character Positions
	public void update() {
		player.update();
	}

	//Paint Method for Drawing Panel using Update
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g); //A formatting method
		Graphics2D g2 = (Graphics2D)g; //Functionality

		tileM.draw(g2);
		player.draw(g2);

		g2.dispose();
	}
}
