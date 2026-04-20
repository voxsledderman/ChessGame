package org.voxsledderman.ui;

import org.voxsledderman.logic.Board;

import java.awt.*;

public class BoardDrawer {
    private final int SQUARE_SIZE = 100;
    private final int OFFSET = 40;

    private final Board board;
    private final Color WHITE_SQUARE_COLOR = new Color(240, 248, 255);
    private final Color BLACK_SQUARE_COLOR = new Color(128, 172, 202);

    public BoardDrawer(Board board) {
        this.board = board;
    }

    public void drawSquares(Graphics2D g2){
        var bArr = board.getBoard();
        drawLabels(g2);

        for(int i = 0; i < bArr.length; i++){
            for(int j = 0; j < bArr[i].length; j++){
                boolean white = (i + j) % 2 == 0;
                var color = white ? WHITE_SQUARE_COLOR : BLACK_SQUARE_COLOR;
                g2.setColor(color);
                g2.fillRect(OFFSET + i * SQUARE_SIZE, OFFSET + j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
    private void drawLabels(Graphics2D g2) {
        g2.setColor(new Color(20, 40, 80));
        g2.setFont(new Font("Arial", Font.BOLD, 18));

        String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (int i = 0; i < 8; i++) {
            g2.drawString(columns[i],
                    OFFSET + i * SQUARE_SIZE + SQUARE_SIZE / 2 - 5,
                    OFFSET + 8 * SQUARE_SIZE + 25);

            g2.drawString(String.valueOf(8 - i),
                    OFFSET - 25,
                    OFFSET + i * SQUARE_SIZE + SQUARE_SIZE / 2 + 7);
        }
    }
}
