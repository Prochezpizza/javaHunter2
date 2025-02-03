package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 64;
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 12;
    public final int maxScreenRow = 8;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 38; //38  12
    public final int maxWorldRow = 26; //26  9
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this, keyHandler);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public UI ui = new UI(this);
    public SuperObject obj[] = new SuperObject[20];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
        tileManager.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Tile
        tileManager.draw(g2);

        // Object
        for(int i = 0; i < obj.length; i++ ){
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // Player
        player.draw(g2);

        // UI 
        ui.draw(g2);

        g2.dispose();
    }
}
