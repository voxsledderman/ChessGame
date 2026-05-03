package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.List;

public class King extends Piece{
    protected King(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        return List.of();
    }
}
