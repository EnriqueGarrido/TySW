package edu.uclm.esi.tysweb.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import edu.uclm.esi.tysweb.websocket.WsServerGames;

public abstract class Game {
	protected List<Match> pendingMatches;
	protected ConcurrentHashMap<UUID, Match> inPlayMatches;
	protected int numberOfPlayers;
	
	public Game(int numberOfPlayers) {
		this.pendingMatches=Collections.synchronizedList(new ArrayList<>());
		this.inPlayMatches=new ConcurrentHashMap<>();
		this.numberOfPlayers=numberOfPlayers;
	}

	public abstract String getName();

	public Match getMatch(Player player) {
		Match match;
		if (this.pendingMatches.size()==0) {
			match=createMatch();
			match.addPlayer(player);
			pendingMatches.add(match);
		} else {
			match=this.pendingMatches.get(0);
			match.addPlayer(player);
			if (match.getPlayers().size()==this.numberOfPlayers) {
				match=this.pendingMatches.remove(0);
				inPlayMatches.put(match.getId(), match);
				WsServerGames.send(match.getPlayers(), match);
				
			}
		}
		return match;
	}

	protected abstract Match createMatch();
}
