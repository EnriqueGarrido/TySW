package edu.uclm.esi.tysweb.games;

import java.util.UUID;

public class Token {
	
	@Bsonable
	private String userName;
	@Bsonable
	private long expiredDate;
	@Bsonable
	private String value;

	public Token(String userName) {
		this.userName = userName;
		this.expiredDate = System.currentTimeMillis() + 5*60*1000;
		this.value=UUID.randomUUID().toString();
	}
}
