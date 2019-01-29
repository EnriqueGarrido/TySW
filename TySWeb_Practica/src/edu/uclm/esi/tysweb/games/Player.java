package edu.uclm.esi.tysweb.games;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.uclm.esi.tysweb.mongobd.dao.DAOPlayer;
import edu.uclm.esi.tysweb.mongodb.MongoBroker;

public class Player {
	
	private String user_name;
	
	private String email;
	
	private String pwd;
	
	private String idGoogle;
	
	@JsonIgnore
	private String photo;
	
	@JsonIgnore
	private Match currentMatch;
	
	
	public String getPhotoStr() {
		return photo;
	}
	
	public void setPhotoSrt(String photo) {
		this.photo = photo;
	}

	public String getIdGoogle() {
		return idGoogle;
	}

	public void setIdGoogle(String idGoogle) {
		this.idGoogle = idGoogle;
	}

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
     }
	
	public Player loginGoogle(String idGoogle, String name, String email) throws Exception {		
		return DAOPlayer.loginGoogle(idGoogle, name, email);
	}
	
	public void insert(String pwd) throws Exception {
		DAOPlayer.insert(this, pwd);
	}
	
	public static void requestToken(String email) throws Exception {
		Player player = DAOPlayer.requestToken(email);
		player.sendToken();
	}
	
	private void sendToken() throws Exception {
		Token token = new Token(this.email);
		token.insert();
		EMailSenderService emailservice = new EMailSenderService();
		emailservice.enviarPorGmail(this.email, token.getToken());
	}
	
	public void changePassword(Token token, String pwd1, String pwd2) throws Exception {
		DAOPlayer.changePassword(this, token, pwd1, pwd2);
		
	}

	public void setPhotoBinary(byte[] bytes) throws Exception {
		DAOPlayer.setPhotoBinary(this, bytes);
	}

	public void setCurrentMatch(Match match) {
		this.currentMatch = match;
	}
	
	public Match getCurrentMatch() {
		return currentMatch;
	}
	
	public boolean tieneTurno() {
		return currentMatch.tieneElTurno(this);
	}

}
