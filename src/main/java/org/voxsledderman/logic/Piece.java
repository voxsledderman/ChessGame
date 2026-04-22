package org.voxsledderman.logic;

import lombok.Getter;
import org.voxsledderman.enums.ChessColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Getter
public abstract class Piece {
    public final ChessColor color;
    public final BufferedImage image;

    protected Piece(ChessColor color, String pathToImage) {
        this.color = color;
        this.image = loadImage(pathToImage);
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
}
