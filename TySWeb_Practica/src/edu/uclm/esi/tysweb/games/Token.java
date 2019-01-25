package edu.uclm.esi.tysweb.games;

import java.util.UUID;

import edu.uclm.esi.tysweb.mongobd.dao.DAOToken;

public class Token {
	
	private String playerEmail;
	private long expiredDate;
	private String value;

	public Token(String userName) {
		this.playerEmail = userName;
		this.expiredDate = System.currentTimeMillis() + 120*60*1000;
		this.value=UUID.randomUUID().toString();
	}
	public Token() {}
	
	public String getToken() {
		return this.value;
	}
	
	public void insert() throws Exception {
		DAOToken.insert(this);
	}
	
	public String getPlayerEmail() {
		return playerEmail;
	}

	public long getExpiredDate() {
		return expiredDate;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getAssociatedEmail() throws Exception {
		return DAOToken.getAssociatedEmail(this);
	}
}
