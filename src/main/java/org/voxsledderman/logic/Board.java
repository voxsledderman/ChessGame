package org.voxsledderman.logic;

import lombok.Getter;
import lombok.Setter;
import org.voxsledderman.enums.ChessColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
public class Board {
    private final HashSet<Piece> piecesSet = new HashSet<>();
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
        if (!(col >= 0 && col < 8 || row >= 0 && row < 8)) return false;

        piece.setRow(row);
        piece.setCol(col);
        return true;
    }

    public Piece getPieceAt(int row, int col){
        for(Piece piece : piecesSet){
            if(piece.getRow() == row && piece.getCol() == col) return piece;
        }
        System.out.println("null here");
        return null;
    }




    private void addPawns(ChessColor color){
        boolean isWhite = color == ChessColor.WHITE;
        int row = isWhite ? 6 : 1;
        String pathToImg = isWhite ? "w-pawn" : "b-pawn";
        for(int col = 0; col < 8; col++){
            piecesSet.add(new Pawn(color, pathToImg, row, col));
        }
    }
    private void addPieces(ChessColor color){
        boolean isWhite = color == ChessColor.WHITE;
        int row = isWhite ? 7 : 0;
        String pathToImg = isWhite ? "w-" : "b-";


        piecesSet.add(new Rook(color, pathToImg + "rook", row, 0));
        piecesSet.add(new Rook(color, pathToImg + "rook", row, 7));
        piecesSet.add(new Knight(color, pathToImg + "knight", row, 1));
        piecesSet.add(new Knight(color, pathToImg + "knight", row, 6));
        piecesSet.add(new Bishop(color, pathToImg + "bishop", row, 2));
        piecesSet.add(new Bishop(color, pathToImg + "bishop", row, 5));
        piecesSet.add(new Queen(color, pathToImg + "queen", row, 3));
        piecesSet.add(new King(color, pathToImg + "king", row, 4));

    }
}
