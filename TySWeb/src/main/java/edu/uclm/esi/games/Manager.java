package edu.uclm.esi.games;

import java.util.concurrent.ConcurrentHashMap;

public class Manager {
	
	private ConcurrentHashMap<String, Player> players;
	

	private Manager() {
		this.players=new ConcurrentHashMap<>();
		//this.partidasPendientes=new ConcurrentHashMap<>();
		//this.partidasEnJuego=new ConcurrentHashMap<>();
	}
	
	private static class ManagerHolder {
		static Manager singleton=new Manager();
	}
	
	public static Manager get() {
		return ManagerHolder.singleton;
	}
	
	public Player login(String email, String pwd) throws Exception {
		Player player = new RegisteredPlayer().login(email, pwd);
		return player;
		//return null;
	}
}
