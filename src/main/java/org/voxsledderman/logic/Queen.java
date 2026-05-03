package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.util.Collection;
import java.util.List;

public class Queen extends Piece{
    protected Queen(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        return List.of();
    }
}
