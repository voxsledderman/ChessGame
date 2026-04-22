package org.voxsledderman.logic;

import lombok.Getter;
import lombok.Setter;
import org.voxsledderman.enums.ChessColor;

@Getter
@Setter
public class Board {
    private final Piece[][] board = new Piece[8][8];

    public Board(){
        setupStartingPositions();
    }

    private void setupStartingPositions(){
        addPawns(ChessColor.WHITE);
        addPieces(ChessColor.WHITE);

        addPawns(ChessColor.BLACK);
        addPieces(ChessColor.BLACK);
    }

    public void movePiece(Piece piece, int row, int col){}

    public Piece getPieceAt(int row, int col){
        return board[row][col];
    }



    private void addPawns(ChessColor color){
        boolean isWhite = color == ChessColor.WHITE;
        int row = isWhite ? 6 : 1;
        String pathToImg = isWhite ? "w-pawn" : "b-pawn";
        for(int col = 0; col < board[0].length; col++){
            Pawn p = new Pawn(color, pathToImg);
            board[row][col] = p;
        }
    }
    private void addPieces(ChessColor color){
        boolean isWhite = color == ChessColor.WHITE;
        int row = isWhite ? 7 : 0;
        String pathToImg = isWhite ? "w-" : "b-";


        board[row][0] = new Rook(color, pathToImg + "rook");
        board[row][7] = new Rook(color, pathToImg + "rook");
        board[row][1] = new Knight(color, pathToImg + "knight");
        board[row][6] = new Knight(color, pathToImg + "knight");
        board[row][2] = new Bishop(color, pathToImg + "bishop");
        board[row][5] = new Bishop(color, pathToImg + "bishop");
        board[row][3] = new Queen(color, pathToImg + "queen");
        board[row][4] = new King(color, pathToImg + "king");

    }
}
