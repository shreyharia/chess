package com.shreyharia.chess.gamelogic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.shreyharia.chess.gamelogic.pieces.Bishop;
import com.shreyharia.chess.gamelogic.pieces.King;
import com.shreyharia.chess.gamelogic.pieces.Knight;
import com.shreyharia.chess.gamelogic.pieces.Pawn;
import com.shreyharia.chess.gamelogic.pieces.Piece;
import com.shreyharia.chess.gamelogic.pieces.Queen;
import com.shreyharia.chess.gamelogic.pieces.Rook;


/**
 *
 * @author Shreya Haria
 */


public class Board {

	private Map<String, Tile> tiles = new HashMap<String, Tile>();
	private boolean turn = true;

	Board() {
		for (int rank = 1; rank <= 8; rank++) {
			for (char file = 'a'; file <= 'h'; file++) {
				tiles.put("" + file + "" + rank, new Tile(file, rank));
			}
		}
		populate();
	}

	//TODO Make this configurable
	private void populate() {
		tiles.get("a1").setPiece(new Rook(true));
		tiles.get("h1").setPiece(new Rook(true));
		tiles.get("a8").setPiece(new Rook(false));
		tiles.get("h8").setPiece(new Rook(false));

		tiles.get("b1").setPiece(new Knight(true));
		tiles.get("g1").setPiece(new Knight(true));
		tiles.get("b8").setPiece(new Knight(false));
		tiles.get("g8").setPiece(new Knight(false));

		tiles.get("c1").setPiece(new Bishop(true));
		tiles.get("f1").setPiece(new Bishop(true));
		tiles.get("c8").setPiece(new Bishop(false));
		tiles.get("f8").setPiece(new Bishop(false));

		tiles.get("d1").setPiece(new Queen(true));
		tiles.get("e1").setPiece(new King(true));
		tiles.get("d8").setPiece(new Queen(false));
		tiles.get("e8").setPiece(new King(false));

		for (char file = 'a'; file <= 'h'; file++) {
			tiles.get("" + file + "" + 2).setPiece(new Pawn(true));
			tiles.get("" + file + "" + 7).setPiece(new Pawn(false));
		}
	}

	private Set<Tile> updatedtiles = new HashSet<Tile>();

	public Set<Tile> move(String msg) {
		String t1 = msg.substring(1, 3);
		String t2 = msg.substring(5, 7);
		Piece p1 = tiles.get(t1).getPiece();
		if (p1 != null && isTileOccupied(msg) && isObstacle(msg) &&  p1.isRechable(msg)) {
			// if move is valid, move pieces and flip the boolean turn
			turn = !turn;
			Tile tile1 = tiles.get(t1);
			Tile tile2 = tiles.get(t2);
			tile2.setPiece(tile1.getPiece());
			tile1.setPiece(null);
			updatedtiles.add(tile1);
			updatedtiles.add(tile2);
		}
		return updatedtiles;
	}

	/**
	 * 
	 * @param msg
	 *            String in format for e.g. Pa1 Pa5
	 * @return Returns true if there is no obstacle between the pieces
	 */
	private boolean isObstacle(String msg) {
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
		int incr = 0;
		int incf = 0;
		if (r2 != r1) {
			incr = absr / (r2 - r1);
		}
		if (f2 != f1) {
			 incf = absf / (f2 - f1);
		}
		int sr = r1;
		int dr = r2 - incr;
		char sf = f1;
		char df = (char) (f2 - incf);

		if (f1 == f2) {
			while (sr != dr) {
				sr += incr;
				if (tiles.get(f1 + "" + sr).getPiece() != null) {
					return false;
				}
			}
			return true;
		} else if (r1 == r2) {
			while (sf != df) {
				sf += incf;
				if (tiles.get(sf + "" + r1).getPiece() != null) {
					return false;
				}
			}
			return true;
		} else if (absr == absf) {
			while ((sr != dr) && (sf != df)) {
				sf += incf;
				sr += incr;
				if (tiles.get(sf + "" + sr).getPiece() != null) {
					return false;
				}				
			}			
			if ((tiles.get(t1).getPiece() instanceof Pawn) && (tiles.get(t2).getPiece() == null)){
				return false;
			}
			return true;
		} else if (((absr == 1) || (absf == 1)) && (absr + absf) == 3) {
			return true;
		}  
		return false;
	}

	private boolean isTileOccupied(String msg) {
		String t1 = msg.substring(1, 3);
		String t2 = msg.substring(5, 7);
		boolean flag = true;
		try {
			Piece p1 = tiles.get(t1).getPiece();
			Piece p2 = tiles.get(t2).getPiece();
			if (p1.isColor() == p2.isColor()) {
				flag = false;
			}
			 
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Map<String, Tile> getTiles() {
		return tiles;
	}

	public boolean getTurn() {
		return turn;
	}
}
