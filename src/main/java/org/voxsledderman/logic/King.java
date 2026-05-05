package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece{
    protected King(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        int row = getRow();
        int col = getCol();

        Move[] potentialMoves = new Move[]{
                Move.from(row + 1, col), Move.from(row + 1, col - 1), Move.from(row + 1, col + 1),
                Move.from(row, col + 1), Move.from(row, col - 1),
                Move.from(row - 1, col + 1), Move.from(row - 1, col), Move.from(row - 1, col -1)
        };

        for(Move move : potentialMoves){
            Piece p = getPieceAt(move.row(), move.col(), pieces);
            if(isMoveInBoard(move) && (p == null || isDifferentColor(p))) moves.add(move);
        }

        return moves;
    }
}
