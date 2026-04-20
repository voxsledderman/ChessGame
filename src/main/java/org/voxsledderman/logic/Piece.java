package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.awt.image.BufferedImage;

public abstract class Piece {
    public final ChessColor color;
    public final BufferedImage image;

    protected Piece(ChessColor color, BufferedImage image) {
        this.color = color;
        this.image = image;
    }
}
