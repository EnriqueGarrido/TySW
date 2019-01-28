package edu.uclm.esi.tysweb.websocket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import edu.uclm.esi.tysweb.games.Match;
import edu.uclm.esi.tysweb.games.Player;

@ServerEndpoint(value="/ws_games", configurator = HttpSessionConfigurator.class)
public class WsServerGames {
	
	static Map<String, Player> players = new HashMap<String, Player>();
	static Map<String, Session> conections = new HashMap<String, Session>();
	
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			JSONObject jso = new JSONObject(message);
			if(jso.get("TYPE").equals("MOVEMENT")) {

			}
		}catch (Exception e) {
			players.remove(session.getId());
			conections.remove(session.getId());
		}
	}

	public static void send(Vector<Player> players, Match match) {
		// TODO Auto-generated method stub
		
	}
	
}
