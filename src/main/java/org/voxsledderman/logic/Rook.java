package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece{
    protected Rook(ChessColor color, String pathToImage , int row, int col) {
        super(color, pathToImage, row, col);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        for(int i = 1; i < 8; i++){

        }

        return moves;
    }
}
