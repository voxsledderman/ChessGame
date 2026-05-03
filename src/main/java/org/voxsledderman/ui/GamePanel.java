package org.voxsledderman.ui;

import org.voxsledderman.Mouse;
import org.voxsledderman.enums.ChessColor;
import org.voxsledderman.logic.Board;
import org.voxsledderman.logic.Move;
import org.voxsledderman.logic.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.voxsledderman.ui.BoardDrawer.OFFSET;
import static org.voxsledderman.ui.BoardDrawer.SQUARE_SIZE;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 900;
    public static final int FPS = 60;

    private Thread gameThread;
    private final Board board = new Board();
    private final BoardDrawer boardDrawer = new BoardDrawer(board);
    private final Mouse mouse = new Mouse();
    private Piece clickedPiece = null;
    private ChessColor turn = ChessColor.WHITE;


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

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
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
        if (!mouse.pressed) {
            if (clickedPiece != null) {
                int col = (mouse.getX() - OFFSET) / SQUARE_SIZE;
                int row = (mouse.getY() - OFFSET) / SQUARE_SIZE;
                if (clickedPiece.getColor() != turn) {
                    clickedPiece = null;
                    return;
                }
                if (board.movePiece(clickedPiece, row, col)) turn = turn.getOther();
            }
            clickedPiece = null;
            return;
        }
        if (clickedPiece == null) {
            int col = (mouse.getX() - OFFSET) / SQUARE_SIZE;
            int row = (mouse.getY() - OFFSET) / SQUARE_SIZE;

            if (col >= 0 && col < 8 && row >= 0 && row < 8) {
                clickedPiece = board.getPieceAt(row, col);
            }
        } else {
            simulate();
        }
    }

    private void simulate() {
        clickedPiece.setX(mouse.x - 40);
        clickedPiece.setY(mouse.y - 50);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        boardDrawer.drawSquares(g2);
        boardDrawer.drawPieces(g2, clickedPiece);
        if(clickedPiece != null && clickedPiece.getColor() == turn) boardDrawer.drawPossibleMoves(g2, clickedPiece);
        if(clickedPiece != null) clickedPiece.draw(g2);
    }
}

