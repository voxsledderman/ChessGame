package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece{
    protected Queen(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        Move[] directions = new Move[]{
                Move.from(1,0), Move.from(-1,0), Move.from(0,-1), Move.from(0,1),
                Move.from(1,1), Move.from(1,-1), Move.from(-1,-1), Move.from(-1,1)

        };
        for(Move dir : directions){
            moves.addAll(getDirectionMoves(dir, pieces));
        }

        return moves;
    }
}
