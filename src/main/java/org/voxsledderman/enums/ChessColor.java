package org.voxsledderman.enums;

public enum ChessColor {
    WHITE,
    BLACK;

    public ChessColor getOther(){
        return this == WHITE ? BLACK : WHITE;
    }
}
