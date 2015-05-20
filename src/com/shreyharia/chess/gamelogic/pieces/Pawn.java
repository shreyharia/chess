package com.shreyharia.chess.gamelogic.pieces;


/**
 *
 * @author Shreya Haria
 */

public class Pawn extends Piece {
	private boolean moved = false;

	public Pawn(boolean color) {
		super(color, "P");
	}

	@Override
	public boolean isRechable(String msg) {
		char f1 = msg.charAt(1);
		int r1 = Integer.parseInt("" + msg.charAt(2));
		char f2 = msg.charAt(5);
		int r2 = Integer.parseInt("" + msg.charAt(6));

		String t1 = msg.substring(1, 3);
		String t2 = msg.substring(5, 7);

		if (t1.equals(t2)) {
			return false;
		}

		int absr = Math.abs(r2 - r1);
		int absf = Math.abs(f2 - f1);

		if ((f1 == f2) || (absf == absr)) {
			if (isColor()) {
				if ((f1 == f2) && (r2 - r1) == 2 && !moved) {
					moved = true;
					return true;
				} else if ((r2 - r1) == 1) {
					moved = true;
					return true;
				}
			} else {
				if ((f1 == f2) && (r2 - r1) == -2 && !moved) {
					moved = true;
					return true;
				} else if ((r2 - r1) == -1) {
					moved = true;
					return true;
				}
			}
		}
		return false;
	}
}
