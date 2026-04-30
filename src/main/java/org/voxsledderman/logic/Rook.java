package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.awt.image.BufferedImage;

public class Rook extends Piece{
    protected Rook(ChessColor color, String pathToImage , int row, int col) {
        super(color, pathToImage, row, col);
    }
}
