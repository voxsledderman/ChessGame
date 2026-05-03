package org.voxsledderman.ui;

import lombok.Getter;
import org.voxsledderman.logic.Board;
import org.voxsledderman.logic.Move;
import org.voxsledderman.logic.Piece;

import java.awt.*;

@Getter
public class BoardDrawer {
    public static final int SQUARE_SIZE = 100;
    public static final int OFFSET = 40;
    public static final int PIECE_SIZE = 100;
    public static final int PIECE_Y_OFFSET = 5;
    public final static int CENTERING_PADDING = (SQUARE_SIZE - PIECE_SIZE) / 2;

    private final Board board;
    private final Color WHITE_SQUARE_COLOR = new Color(240, 248, 255);
    private final Color BLACK_SQUARE_COLOR = new Color(128, 172, 202);

    public BoardDrawer(Board board) {
        this.board = board;
    }

    public void drawSquares(Graphics2D g2){
        drawLabels(g2);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
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

    public void drawPieces(Graphics2D g2, Piece toBeSkipped) {

        for(Piece piece : board.getPiecesSet()){
            if(piece.equals(toBeSkipped)) continue;

            int yPixel = OFFSET + piece.getRow() * SQUARE_SIZE + CENTERING_PADDING - PIECE_Y_OFFSET;
            int xPixel = OFFSET + piece.getCol() * SQUARE_SIZE + CENTERING_PADDING;

            g2.drawImage(piece.getImage(), xPixel, yPixel, PIECE_SIZE, PIECE_SIZE, null);
        }
    }
    public void drawPossibleMoves(Graphics2D g2, Piece clickedPiece) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color gray = new Color(85, 85, 85, 70);
        g2.setColor(gray);
        int outerSize = (int) (SQUARE_SIZE * 0.6);
        int outerOffset = (SQUARE_SIZE - outerSize) / 2;
        g2.setStroke(new BasicStroke(2f));

        int innerSize = (int) (SQUARE_SIZE * 0.20);
        int innerOffset = (SQUARE_SIZE - innerSize) / 2;

        for (Move move : clickedPiece.getMoves(board.getPiecesSet())) {
            int cellX = OFFSET + move.col() * SQUARE_SIZE;
            int cellY = OFFSET + move.row() * SQUARE_SIZE;
            g2.drawOval(cellX + outerOffset, cellY + outerOffset, outerSize, outerSize);
            g2.fillOval(cellX + innerOffset, cellY + innerOffset, innerSize, innerSize);
        }
        g2.setStroke(new BasicStroke(1.0f));
    }
}
