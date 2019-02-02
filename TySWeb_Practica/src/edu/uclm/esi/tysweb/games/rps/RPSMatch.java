package edu.uclm.esi.tysweb.games.rps;

import edu.uclm.esi.tysweb.games.Match;
import edu.uclm.esi.tysweb.games.Player;
import edu.uclm.esi.tysweb.games.Result;
import edu.uclm.esi.tysweb.mongobd.dao.DAOMatch;

public class RPSMatch extends Match{

	public RPSMatch() {
		super();
		this.board= new RPSBoard(this); 
	}
	
	@Override
	public void calculateFirstPlayer() {
		currentPlayer = 0;
	}
	
	public boolean tieneElTurno(Player player) {
		if(currentPlayer == players.indexOf(player) || currentPlayer == -1)
			return true;
		return false;
	}
	@Override
	protected void save() throws Exception{
		Result result = new Result(this);
		DAOMatch.insert(result);
	}

	@Override
	public void quitgame(Player player) {
		int winnerIndex = (this.players.indexOf(player) + 1) % this.players.size();
		this.winner = this.players.get(winnerIndex);
		try {
			save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.players.remove(player);
	}
}
