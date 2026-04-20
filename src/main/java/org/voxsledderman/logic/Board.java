package org.voxsledderman.logic;

import lombok.Getter;
import lombok.Setter;
import org.voxsledderman.enums.ChessColor;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class Board {
    private final Piece[][] board = new Piece[8][8];

    public Board(){
        setupStartingPositions();
    }

    private void setupStartingPositions(){
        addPawns(ChessColor.WHITE, null);
        addPieces(ChessColor.WHITE);

        addPawns(ChessColor.BLACK, null);
        addPieces(ChessColor.BLACK);
    }

    public void movePiece(Piece piece, int x, int y){}

    public Piece getPieceAt(int x, int y){
        return board[x][y];
    }



    private void addPawns(ChessColor color, BufferedImage image){
        int x = color == ChessColor.WHITE ? 6 : 1;
        for(int i = 0; i < board[0].length; i++){
            Pawn p = new Pawn(color, image);
            board[x][i] = p;
        }
    }
    private void addPieces(ChessColor color){
        int x = color == ChessColor.WHITE ? 7 : 0;

        board[x][0] = board[x][7] = new Rook(color, null);
        board[x][1] = board[x][6] = new Knight(color, null);
        board[x][2] = board[x][5] = new Bishop(color, null);
        board[x][3] = new Queen(color, null);
        board[x][4] = new King(color, null);

    }
}
