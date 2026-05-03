package org.voxsledderman.logic;

import lombok.Getter;
import lombok.Setter;
import org.voxsledderman.enums.ChessColor;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {
    private final List<Piece> piecesList = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();
    public Board(){
        setupStartingPositions();
    }

    private void setupStartingPositions(){
        addPawns(ChessColor.WHITE);
        addPieces(ChessColor.WHITE);

        addPawns(ChessColor.BLACK);
        addPieces(ChessColor.BLACK);
    }

    public boolean movePiece(Piece piece, int row, int col){
        if(piece == null) return false;
        if (!(col >= 0 && col < 8 && row >= 0 && row < 8)) return false;
        if(!piece.isMoveLegal(piecesList, row, col)) return false;
        Piece capturedPiece = getPieceAt(row, col);
        if(capturedPiece != null && (piece == capturedPiece || piece.getColor() == capturedPiece.getColor())) return false;
        captureIfNeeded(row, col);

        piece.setRow(row);
        piece.setCol(col);
        return true;
    }
    private void captureIfNeeded(int row, int col){
        Piece capturedPiece = getPieceAt(row, col);
        if(capturedPiece == null) return;
        piecesList.remove(capturedPiece);
        capturedPieces.add(capturedPiece);
    }

    public Piece getPieceAt(int row, int col){
        for(Piece piece : piecesList){
            if(piece.getRow() == row && piece.getCol() == col) return piece;
        }
        return null;
    }




    private void addPawns(ChessColor color){
        boolean isWhite = color == ChessColor.WHITE;
        int row = isWhite ? 6 : 1;
        String pathToImg = isWhite ? "w-pawn" : "b-pawn";
        for(int col = 0; col < 8; col++){
            piecesList.add(new Pawn(color, pathToImg, row, col));
        }
    }
    private void addPieces(ChessColor color){
        boolean isWhite = color == ChessColor.WHITE;
        int row = isWhite ? 7 : 0;
        String pathToImg = isWhite ? "w-" : "b-";


        piecesList.add(new Rook(color, pathToImg + "rook", row, 0));
        piecesList.add(new Rook(color, pathToImg + "rook", row, 7));
        piecesList.add(new Knight(color, pathToImg + "knight", row, 1));
        piecesList.add(new Knight(color, pathToImg + "knight", row, 6));
        piecesList.add(new Bishop(color, pathToImg + "bishop", row, 2));
        piecesList.add(new Bishop(color, pathToImg + "bishop", row, 5));
        piecesList.add(new Queen(color, pathToImg + "queen", row, 3));
        piecesList.add(new King(color, pathToImg + "king", row, 4));

    }
}
