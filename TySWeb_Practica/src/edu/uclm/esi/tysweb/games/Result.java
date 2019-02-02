package edu.uclm.esi.tysweb.games;

public class Result {
	

	private String userName1;

	private String userName2;

	private String winner;
	
	private float time;

	public Result(Match match) {
		this.userName1 = match.players.get(0).getEmail();
		this.userName2 = match.players.get(1).getEmail();
		this.winner = match.winner.getEmail();
		this.time = System.currentTimeMillis();
	}

	public String getUserName1() {
		return userName1;
	}

	public String getUserName2() {
		return userName2;
	}

	public String getWinner() {
		return winner;
	}

	public float getTime() {
		return time;
	}
}
