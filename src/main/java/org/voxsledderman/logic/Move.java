package org.voxsledderman.logic;

public record Move(int row, int col) {

    public static Move from(int row, int col){
        return new Move(row, col);
    }
}
