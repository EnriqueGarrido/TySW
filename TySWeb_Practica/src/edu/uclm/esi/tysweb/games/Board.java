package edu.uclm.esi.tysweb.games;

public abstract class Board {
	
	protected Match match;
	
	public Board(Match match) {
		this.match=match;
	}

	public abstract void move(Player player, int[] coordinates) throws Exception;
	public abstract Player getWinner();
	public abstract boolean end();
}
