package com.shreyharia.chess.gamelogic.pieces;

/**
 *
 * @author Shreya Haria
 */

public abstract class Piece {
    private boolean color;
    private String symbol;

    public Piece(boolean color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }


    public boolean isColor() {
        return color;
    }

    public String getSymbol() {
        return symbol;
    }


    @Override
    public String toString() {
        return "Piece{" + "color=" + color + '}';
    }

    abstract public boolean isRechable(String msg);
}
