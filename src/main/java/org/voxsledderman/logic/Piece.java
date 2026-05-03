package org.voxsledderman.logic;

import lombok.Getter;
import lombok.Setter;
import org.voxsledderman.enums.ChessColor;
import org.voxsledderman.ui.BoardDrawer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public abstract class Piece {
    private final ChessColor color;
    private final BufferedImage image;
    private int x, y;
    private int row, col;

    protected Piece(ChessColor color, String pathToImage, int row, int col) {
        this.color = color;
        this.image = loadImage(pathToImage);
        this.row = row;
        this.col = col;
    }

    public BufferedImage loadImage(String imageName) {
        try {
            File imgFile = new File("assets/piece/" + imageName + ".png");

            if (!imgFile.exists()) {
                throw new IOException("Nie znaleziono pliku w folderze assets: " + imgFile.getAbsolutePath());
            }
            return ImageIO.read(imgFile);
        } catch (IOException e) {

            throw new RuntimeException("Błąd ładowania obrazu z dysku: " + e.getMessage(), e);
        }
    }

    public abstract List<Move> getMoves(Collection<Piece> pieces);


    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, BoardDrawer.SQUARE_SIZE, BoardDrawer.SQUARE_SIZE, null);
    }
    protected boolean isMoveInBoard(Move move){
        return move.col() >= 0 && move.col() < 8 && move.row() >= 0 && move.row() < 8;
    }
    protected Piece getPieceAt(int r, int c, Collection<Piece> pieces) {
        for (Piece p : pieces) {
            if (p.getRow() == r && p.getCol() == c) {
                return p;
            }
        }
        return null;
    }
    public boolean isMoveLegal(Collection<Piece> pieces, int row, int col){
        return getMoves(pieces).contains(new Move(row, col));
    }
}
