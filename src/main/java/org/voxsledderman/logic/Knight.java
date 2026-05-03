package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece{
    protected Knight(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        List<Move> moves = new ArrayList<>();

        int row = getRow();
        int col = getCol();
        Move[] combinations = new Move[]{Move.from(row + 2,col + 1), Move.from(row + 2,col -1),
                Move.from(row -2,col + 1), Move.from(row -2,col -1),
                Move.from(row + 1,col + 2), Move.from(row -1,col + 2),
                Move.from(row + 1,col - 2), Move.from(row -1,col -2)
        };
        for(Move move : combinations){
            if(isMoveInBoard(move) && isDifferentColor(getPieceAt(move.row(), move.col(), pieces))){
                moves.add(move);
            }
        }
        return moves;
    }
}
