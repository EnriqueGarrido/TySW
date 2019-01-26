package edu.uclm.esi.tysweb.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import org.json.JSONObject;

import edu.uclm.esi.tysweb.games.Player;


@ServerEndpoint(value="/ws_server")
public class WsServer {

	static Set<Session> players = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("OPEN WS");
		players.add(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			JSONObject jso = new JSONObject(message);
			if(jso.get("TYPE").equals("photo")) {
				System.out.println("FOTO!!!");
			} else if(jso.get("TYPE").equals("chat")) {
				System.out.println("CHAT!!!");
			}
		}catch (Exception e) {
			
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		players.remove(session);
	}
	
	/*@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {//SE EJECUTA CUANDO SE ESTABLECE EL HANDSHAKE
		sessionsById.put(session.getId(), session);
		Player player = (Player) session.getAttributes().get("player");
		String userName=player.getUserName();
		if (sessionsByPlayer.get(userName)!=null) {
			sessionsByPlayer.remove(userName);
		}
		sessionsByPlayer.put(userName, session);
	}
	
	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
		Player player = (Player) session.getAttributes().get("player");
		byte[] bytes = message.getPayload().array();
		try {
			player.setPhotoBinary(bytes);
		} catch (Exception e) {
			// Envio de mensaje de vuelta avisando del error
			e.printStackTrace();
		}
	}*/
}
