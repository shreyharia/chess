package com.shreyharia.chess.gamelogic;

import com.shreyharia.chess.gamelogic.pieces.Piece;


/**
 *
 * @author Shreya Haria
 */

//TODO put more intelligence auxillary piece, isOriginal.. etc..
public class Tile {
    private Piece piece;
    private char file;
    private int rank;

    public Tile(char file, int rank) {
        this.file = file;
        this.rank = rank;
        this.piece = null;
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Tile{" + "piece=" + piece + '}';
    }
}
