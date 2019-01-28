package edu.uclm.esi.tysweb.games.rps;

import edu.uclm.esi.tysweb.games.Match;
import edu.uclm.esi.tysweb.games.Player;
import edu.uclm.esi.tysweb.games.Result;

public class RPSMatch extends Match{

	public RPSMatch() {
		super();
		this.board= new RPSBoard(this); 
	}
	@Override
	public void calculateFirstPlayer() {

	}
	public boolean tieneElTurno(Player player) {
		return true;
	}
	@Override
	protected void save() throws Exception{
		Result result = new Result(this.getPlayers().get(0).getUserName(), this.getPlayers().get(1).getUserName(),this.winner.getUserName());
		//MongoBroker.get().insert(result);
	}
}
