package edu.uclm.esi.tysweb.web;

import edu.uclm.esi.tysweb.games.Player;

public class Manager {
	
	
	
	private static class ManagerHolder {
		static Manager singleton=new Manager();
	}
	
	public static Manager get() {
		return ManagerHolder.singleton;
	}
	
	public Player login(String email, String pwd) throws Exception {
		Player usuario = new Player().login(email, pwd);
		return usuario;
	}
	
}
