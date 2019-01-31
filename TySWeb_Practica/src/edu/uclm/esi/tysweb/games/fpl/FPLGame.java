package edu.uclm.esi.tysweb.games.fpl;

import edu.uclm.esi.tysweb.games.Game;
import edu.uclm.esi.tysweb.games.Match;

public class FPLGame extends Game{

	public FPLGame() {
		super(2);
	}

	@Override
	public String getName() {
		return "Find Letters.";
	}

	@Override
	protected Match createMatch() {
		return new FPLMatch();
	}
	
	
}
