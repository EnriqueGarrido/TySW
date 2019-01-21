package edu.uclm.esi.tysweb.games;

import edu.uclm.esi.tysweb.mongobd.dao.DAOPlayer;

public class Player {
	
	private String user_name;
	
	private String email;
	
	private String pwd;
	
	
	public void setUserName(String name) {
		this.user_name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.pwd = password;
	}
	
	public String getUserName() {
		return user_name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Player login(String email, String pwd)throws Exception {
        return DAOPlayer.login(email,pwd);
    	//return null;
     }
	
}
