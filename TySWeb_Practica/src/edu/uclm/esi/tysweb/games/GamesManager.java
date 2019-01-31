package edu.uclm.esi.tysweb.games;

import java.util.Hashtable;

import edu.uclm.esi.tysweb.games.fpl.FPLGame;
import edu.uclm.esi.tysweb.games.rps.RPSGame;

public class GamesManager {
	
	private Hashtable<String, Game> games;
	
	public GamesManager() {
		games = new Hashtable<>();
		Game rps = new RPSGame();
		games.put(rps.getName(), rps);
		Game pair_of_letters = new FPLGame();
		games.put(pair_of_letters.getName(), pair_of_letters);
	}

	private static class ManagerHolder {
		static GamesManager singleton=new GamesManager();
	}
	
	public static GamesManager get() {
		return ManagerHolder.singleton;
	}
	
	public Match joinGame(Player player, String gameName) {
		Game game=this.games.get(gameName);
		return game.getMatch(player);
	}
	
}
