package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece{
    protected Bishop(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        Move[] directions = new Move[]{
                Move.from(1,1), Move.from(1,-1), Move.from(-1,-1), Move.from(-1,1)
        };
        for(Move dir : directions){
            moves.addAll(getDirectionMoves(dir, pieces));
        }

        return moves;
    }
    private List<Move> getDirectionMoves(Move dir, Collection<Piece> pieces){
        List<Move> dirMoves = new ArrayList<>();
        for(int i = 1; i < 8; i++){
            Move move = Move.from(getRow() + (i * dir.row()), getCol() + (i * dir.col()));

            if(isMoveInBoard(move)){
                Piece pieceAt = getPieceAt(move.row(), move.col(), pieces);
                if(pieceAt == null){
                    dirMoves.add(move);
                } else {
                    if(isDifferentColor(pieceAt)){
                        dirMoves.add(move);
                    }
                    break;
                }
            } else break;
        }
        return dirMoves;
    }
}