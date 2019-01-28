package edu.uclm.esi.tysweb.games.rps;

import edu.uclm.esi.tysweb.games.Game;
import edu.uclm.esi.tysweb.games.Match;

public class RPSGame extends Game {
	
	public RPSGame() {
		super(2);
	}

	@Override
	public String getName() {
		return "Rock, Paper, Scissors.";
	}

	@Override
	protected Match createMatch() {
		return new RPSMatch();
	}
}
