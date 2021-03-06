package com.shreyharia.chess.gamelogic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Shreya Haria
 */


public class Game {

    private String player1;
    private String player2;
    private Board board;
    private Set<Tile> updates = new HashSet<Tile>(); 
	//Observer Pattern
    private Set<String> spectators = new HashSet<String>();
    
    
    
    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();
    }

    public void setPlayer1(String player1) {
    	this.player1 = player1;
    	spectators.add(player1);
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    	spectators.add(player2);
    }

    public String getPlayer1() {
    	return player1; 
    }

    public String getPlayer2() {
        return player2;
    }
    
    public void addSpectator(String user){
		spectators.add(user);
	}
    
    public Set<String> getSpectators(){    	
    	return Collections.unmodifiableSet(spectators);
    }
    
    public boolean move(String user, String message) {
        if ( board.getTurn() && user.equals(player1)) {
            updates = board.move(message);
            return true;
        } else if ((!board.getTurn()) && user.equals(player2)) {
            updates = board.move(message);
            return true;
        }
        return false;
    }

    public Map<String, String> getallpieces() {
        Set<Tile> aSet = new HashSet<Tile>();
        aSet.addAll(board.getTiles().values());
        return Set2Map(aSet);
    }

    private static Map<String, String> Set2Map(Set<Tile> tiles) {
        Map<String, String> aMap = new HashMap<String, String>();
        for (Tile tile : tiles) {
            String id = "" + tile.getFile() + "" + tile.getRank();
            String p_col = null;
            String p = null;
            try {
                p_col = tile.getPiece().isColor() ? "W" : "B";
                p = tile.getPiece().getSymbol();
            } catch (NullPointerException e) {
                p_col = p = " ";
				e.printStackTrace();
            } finally {
                aMap.put(id, "" + p_col + "" + p);
            }
        }
        return aMap;
    }
    
    public Map<String, String> getLastUpdate() {
		Map<String, String> aMap = Set2Map(updates);
    	updates.clear();
		return aMap;
	}
}
