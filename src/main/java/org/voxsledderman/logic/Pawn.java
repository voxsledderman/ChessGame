package org.voxsledderman.logic;

import org.voxsledderman.enums.ChessColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(ChessColor color, String pathToImage, int x, int y) {
        super(color, pathToImage, x, y);
    }

    //TODO: En-passant, promotion after reaching final square
    @Override
    public List<Move> getMoves(Collection<Piece> pieces) {
        List<Move> moves = new ArrayList<>();
        int startingRow = getColor() == ChessColor.WHITE ? 6 : 1;
        int direction = getColor() == ChessColor.WHITE ? -1 : 1;
        int forwardRow = getRow() + direction;

        Move forwardMove = Move.from(forwardRow, getCol());
        Move doubleForwardMove = Move.from(forwardRow + direction, getCol());
        Move captureL = Move.from(forwardRow, getCol() - 1);
        Move captureR = Move.from(forwardRow, getCol() + 1);

        Piece pieceAtForward = getPieceAt(forwardRow, getCol(), pieces);
        Piece pieceAtL = getPieceAt(forwardRow, getCol() - 1, pieces);
        Piece pieceAtR = getPieceAt(forwardRow, getCol() + 1, pieces);
        Piece pieceAtDoubleForward = getPieceAt(forwardRow + direction, getCol(), pieces);

        if (isMoveInBoard(forwardMove) && pieceAtForward == null) {
            moves.add(forwardMove);
        }
        if (isMoveInBoard(captureL) && pieceAtL != null && isDifferentColor(pieceAtL)) {
            moves.add(captureL);
        }
        if (isMoveInBoard(captureR) && pieceAtR != null && isDifferentColor(pieceAtR)) {
            moves.add(captureR);
        }
        if(pieceAtForward == null && pieceAtDoubleForward == null && getRow() == startingRow){
            moves.add(doubleForwardMove);
        }
        return moves;
    }
}
