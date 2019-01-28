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
	
	public void register(String email, String pwd, String user) throws Exception {
		Player usuario=new Player();
		usuario.setEmail(email);
		usuario.setUserName(user);
		//usuario.setScore(0);
		//usuario.setPhoto("profile/default.png");
		usuario.insert(pwd);
	}
	
	public Player loginGoogle(String idGoogle, String name, String email) throws Exception {
		Player player = new Player();
		return player.loginGoogle(idGoogle, name, email);
	}
	
	
	
}
