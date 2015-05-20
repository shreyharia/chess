package com.shreyharia.chess.gamelogic.pieces;


/**
 *
 * @author Shreya Haria
 */

public class King extends Piece {

    public King(boolean color) {
        super(color,"K");
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
    	
    	if ((absr == 1 || absf == 1 ) && ((f1 == f2) || (r1 == r2) || (absr == absf))) {
			return true;
		}else{
			return false;
		}

    }
}
