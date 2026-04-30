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
import java.util.Objects;

@Getter
@Setter
public abstract class Piece {
    public final ChessColor color;
    public final BufferedImage image;
    public int x, y;
    public int row, col;

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

    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, BoardDrawer.SQUARE_SIZE, BoardDrawer.SQUARE_SIZE, null);
    }
}
