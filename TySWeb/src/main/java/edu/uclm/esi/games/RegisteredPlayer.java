package edu.uclm.esi.games;

import edu.uclm.esi.mongolabels.dao.DAOPlayer;

public class RegisteredPlayer extends Player {
	
	public RegisteredPlayer() {
		
	}
	
    Player login(String email, String pwd)throws Exception {
        return DAOPlayer.login(email,pwd);
    	//return null;
     }
	
    //Usuario loginGoogle(String email, String token)throws Exception {
        //return DAOUsuario.loginGoogle(email,token);
     //}
}
