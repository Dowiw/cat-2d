package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import src.entity.Player;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16; //Default Size for a tile (pixel based)
    final int scale = 3; //Pixel Scaler

    public final int tileSize = originalTileSize * scale; //Default Size for a scaled tile

    //Screen size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    //Runners
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    //Game Panel method
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
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
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();

                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    //Update Method
    public void update() {

        player.update();
    }
    
    //Paint Method
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();

    }
}
