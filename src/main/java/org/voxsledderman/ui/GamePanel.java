package org.voxsledderman.ui;

import org.voxsledderman.logic.Board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 900;
    public static final int FPS = 60;

    Thread gameThread;
    Board board = new Board();
    BoardDrawer boardDrawer = new BoardDrawer(board);


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.white);
        this.setDoubleBuffered(true);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }
    public void launch() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void update() {
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        boardDrawer.drawSquares(g2);
        boardDrawer.drawPieces(g2);
    }
}
