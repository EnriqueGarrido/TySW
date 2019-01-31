package edu.uclm.esi.tysweb.games.fpl;

import java.util.ArrayList;
import java.util.Collections;

import edu.uclm.esi.tysweb.games.Board;
import edu.uclm.esi.tysweb.games.Player;

public class FPLBoard extends Board{
	
	private char[] board_state;
	
	public char[] getBoard_state() {
		return board_state;
	}

	public FPLBoard(FPLMatch fplmatch) {
		super(fplmatch);
		this.board_state = fullfillBoard();
	}
	
	@Override
	public void move(Player player, int[] coordinates) throws Exception {
		// TODO Auto-generated method stub
		
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
