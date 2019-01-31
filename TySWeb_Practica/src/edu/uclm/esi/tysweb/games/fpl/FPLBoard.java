package edu.uclm.esi.tysweb.games.fpl;

import java.util.ArrayList;
import java.util.Collections;

import edu.uclm.esi.tysweb.games.Board;
import edu.uclm.esi.tysweb.games.Player;

public class FPLBoard extends Board{
	
	private char[] board_state;
	private int score0;
	private int score1;
	private int[] pair;
	
	public int[] getPair() {
		return pair;
	}

	public char[] getBoard_state() {
		return board_state;
	}

	public int getScore0() {
		return score0;
	}

	public int getScore1() {
		return score1;
	}

	public FPLBoard(FPLMatch fplmatch) {
		super(fplmatch);
		this.board_state = fullfillBoard();
		this.score0 = 0;
		this.score1 = 0;
		this.pair = new int[2];
	}
	
	@Override
	public void move(Player player, int[] coordinates) throws Exception {
		pair[0]=-1; pair[1]=-1;		// Control values, used to comunicate the pairs to clients
		int option1 = coordinates[0], option2 = coordinates[1];
		if (board_state[option1] == board_state[option2]) { // The pair is correct
			pair[0]=option1; pair[1]=option2;	// if pair is matched, then is comunitated to clients
			if(this.match.getPlayers().get(0) == player) {
				score0++;
			}else {
				score1++;
			}
		}
	}

	@Override
	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean end() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private char[] fullfillBoard() {
		char[] fullfilled;		
		int numeroMax=50; // Half of the board as letters are shown twice
		int num1Minus=97;
		int num2Minus=122;
		int num1Mayus=65;
		int num2Mayus=90;

		ArrayList<Character> vector = new ArrayList<Character>();

		for (int i=0; i<numeroMax;i++) {
			int letraAleatoriaMinus = (int)Math.floor(Math.random()*(num2Minus -num1Minus)+num1Minus);
			int letraAleatoriaMayus = (int)Math.floor(Math.random()*(num2Mayus -num1Mayus)+num1Mayus);
			if(i<(numeroMax/2)) {
				vector.add((char)letraAleatoriaMinus);
				vector.add((char)letraAleatoriaMinus);
			}else {
				vector.add((char)letraAleatoriaMayus);
				vector.add((char)letraAleatoriaMayus);
			}			
		}
		
		//Collections.shuffle(vector);
		
		fullfilled = new char[vector.size()];
		
		for(int i = 0; i < vector.size(); i++) {
			fullfilled[i] = vector.get(i);
		}
		
		return fullfilled;
	}
}
