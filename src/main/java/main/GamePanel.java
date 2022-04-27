package main;

import controls.KeyboardControler;
import controls.MouseController;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS

    public final int originalTileSize = 16;
    public int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public int tileColumns = 20;
    public int tileRows = 14;
    public final int screenWidth = tileColumns * tileSize;
    public final int screenHeight = tileRows * tileSize;

    public final int maxWorldColumns = 50;
    public final int maxWorldRows = 50;
    public  final int worldWidth = maxWorldColumns * tileSize;
    public  final int worldHeight = maxWorldRows * tileSize;

    public final int FPS = 60;


    Thread gameThread;
    MouseController mCtrl = new MouseController();
    KeyboardControler kCtrl = new KeyboardControler();
    public Player player = new Player(this, mCtrl, kCtrl);
    TileManager tileManager = new TileManager(this, mCtrl);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public GamePanel() {

        //WINDOW PROPERTIES
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        //COMPONENTS
        this.addMouseListener(mCtrl);
        this.addKeyListener(kCtrl);
        this.setFocusable(true);



    }
    public void zoom() {








    }



    public void initialisation() {





        //GAME THREAD SETUP
        gameThread = new Thread(this);
        gameThread.start();

    }

//    @Override
//    public void run() {
//
//        long frameTime = 1000000000/FPS; // 0.016 SEC
//        long nextDrawTime = System.nanoTime() + frameTime;
//
//
//
//
//        while (gameThread != null) {
//
//            //UPDATING GAME AND PLAYER DATA
//            update();
//
//            //REDRAWING THE GAME
//            repaint();
//
//           // System.out.println("I work");
//
//        long remainingTime = nextDrawTime - System.nanoTime();
//        remainingTime = remainingTime / 1000000;
//
//
//        if (remainingTime < 0) {
//            remainingTime = 0;
//        }
//
//            try {
//
//                Thread.sleep(remainingTime);
//                nextDrawTime += frameTime;
//
//
//
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }

public void run() {

    double frameTime = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    int timer = 0;
    int drawCount = 0;

    while (gameThread !=null) {

        currentTime = System.nanoTime();


        delta += (currentTime - lastTime) / frameTime;
        timer += (currentTime - lastTime);
        lastTime = currentTime;



        if (delta >=1) {
            update();
            repaint();
            drawCount++;
            delta --;
        }
        if (timer >= 1000000000) {
          //  System.out.println("FPS" + drawCount);
            drawCount = 0;
            timer = 0;
        }
        }
    }

    public void update() {

        player.update();








    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
