package org.voxsledderman.ui;

import org.voxsledderman.Mouse;
import org.voxsledderman.logic.Board;
import org.voxsledderman.logic.Piece;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 900;
    public static final int FPS = 60;

    private Thread gameThread;
    private final Board board = new Board();
    private final BoardDrawer boardDrawer = new BoardDrawer(board);
    private final Mouse mouse = new Mouse();
    private Piece clickedPiece = null;


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.white);
        this.setDoubleBuffered(true);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
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
        if(!mouse.pressed){
            if(clickedPiece != null){
                int col = (mouse.getX() - BoardDrawer.OFFSET) / BoardDrawer.SQUARE_SIZE;
                int row = (mouse.getY() - BoardDrawer.OFFSET) / BoardDrawer.SQUARE_SIZE;
                board.movePiece(clickedPiece, row, col);
            }
            clickedPiece = null;
            return;
        }
        if(clickedPiece == null) {
            int col = (mouse.getX() - BoardDrawer.OFFSET) / BoardDrawer.SQUARE_SIZE;
            int row = (mouse.getY() - BoardDrawer.OFFSET) / BoardDrawer.SQUARE_SIZE;

            if (col >= 0 && col < 8 && row >= 0 && row < 8) {
                clickedPiece = board.getPieceAt(row, col);
            }
        } else {
            simulate();
        }
    }

    private void simulate(){
        clickedPiece.x = mouse.x - 40;
        clickedPiece.y = mouse.y - 50;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        boardDrawer.drawSquares(g2);
        boardDrawer.drawPieces(g2);
        if(clickedPiece != null) clickedPiece.draw(g2);
    }
}
