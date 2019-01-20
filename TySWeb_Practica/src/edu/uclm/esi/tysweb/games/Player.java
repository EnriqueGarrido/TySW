package edu.uclm.esi.tysweb.games;

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
	
}
