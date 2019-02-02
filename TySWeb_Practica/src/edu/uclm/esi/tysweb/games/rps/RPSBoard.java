package edu.uclm.esi.tysweb.games.rps;

import edu.uclm.esi.tysweb.games.Board;
import edu.uclm.esi.tysweb.games.Player;

public class RPSBoard extends Board{
	
	private final static int PIEDRA = 0;
	private final static int PAPEL = 1;
	private final static int TIJERA = 2;
	private int[] tiradas0, tiradas1;

	public RPSBoard(RPSMatch rpsmatch) {
		super(rpsmatch); 
		this.tiradas0 = new int[] { -1, -1, -1 };
		this.tiradas1 = new int[] { -1, -1, -1 };
	}

	@Override
	public void move(Player player, int[] coordinates) throws Exception {
		int pos;
		if (this.match.getPlayers().get(0) == player) { // si es la misma referencia que player hago una cosa sino la
														// otra.
			pos = rellenar(tiradas0, coordinates[0]);
		} else {
			pos = rellenar(tiradas1, coordinates[0]);
		}
	}

	private int rellenar(int[] tiradas, int valor) {
		for (int i = 0; i < tiradas.length; i++) {
			if (tiradas[i] == -1) {
				tiradas[i] = valor;
				return i;
			}
		}
		return -1;

	}

	@Override
	public Player getWinner() {
			for (int i = 0; i < tiradas0.length; i++) 
				if (tiradas0[i] == -1 || tiradas1[i] == -1) 
					return null;
			
			return gana(tiradas0,tiradas1);

	}
//		}


	private Player gana(int[] a, int[] b) {
		int victoriasA=0, victoriasB=0;
		for(int i=0; i<a.length;i++) {
			if(gana(a[i],b[i]))
				victoriasA++;
			else if(gana(b[i], a[i]))
				victoriasB++;
		}
		return victoriasA>victoriasB ? this.match.getPlayers().get(0) : 
			victoriasB>victoriasA ? this.match.getPlayers().get(1) : new Player("TIE_PLAYER");
	}
	private boolean gana(int a, int b) {
		if (a==PIEDRA && b == TIJERA) {
			return true;
		}
		if (a==PAPEL && b == PIEDRA) {
			return true;
		}
		if (a==TIJERA && b == PAPEL) {
			return true;	
		}
		return false;
	}

	public int[] getTiradas0() {
		return tiradas0;
	}

	public int[] getTiradas1() {
		return tiradas1;
	}

	@Override
	public boolean end() {
		if(this.getWinner()!=null)
			return true;
		for(int i=0; i<tiradas0.length;i++)
			if(tiradas0[i]== -1 || tiradas1[i]== -1)
				return false;
		return true;
	}
}
