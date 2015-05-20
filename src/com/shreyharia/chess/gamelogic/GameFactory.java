/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shreyharia.chess.gamelogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Shreya Haria
 */

//TODO Make Factory using builder pattern
public class GameFactory {

    private static final Map<Integer, Game> games = new HashMap<Integer, Game>();
    
    public static Game getGame(Integer gameid) {
        if (games.containsKey(gameid)) {
            return games.get(gameid);
        } else //to be changed
        {
            return null;
        }
    }

    public static Integer newGame() {
        Integer gameid;
        do {
            gameid = (int) (Math.random() * 1000);
        } while (games.containsKey(gameid));
        Game game = new Game(null, null);
        games.put(gameid, game);
        return gameid;
    }

    public static Set<Integer> getGames(){
        return games.keySet();
    }
}
