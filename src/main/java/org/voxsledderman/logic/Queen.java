package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.awt.image.BufferedImage;

public class Queen extends Piece{
    protected Queen(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }
}
