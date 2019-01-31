package edu.uclm.esi.tysweb.games.fpl;

import edu.uclm.esi.tysweb.games.Match;
import edu.uclm.esi.tysweb.games.Player;
import edu.uclm.esi.tysweb.games.Result;
import edu.uclm.esi.tysweb.games.rps.RPSBoard;

public class FPLMatch extends Match{

	public FPLMatch() {
		super();
		this.board = new FPLBoard(this);
	}

	@Override
	public void calculateFirstPlayer() {
		//currentPlayer = 0;
	}

	@Override
	public boolean tieneElTurno(Player player) {
		if(currentPlayer == players.indexOf(player) || currentPlayer == -1)
			return true;
		return false;
	}
	
	@Override
	protected void save() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
