package edu.uclm.esi.tysweb.websocket;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import org.json.JSONObject;

import edu.uclm.esi.tysweb.games.Player;


@ServerEndpoint(value="/ws_server", configurator = HttpSessionConfigurator.class)
public class WsServer {

	//static Set<HttpSession> players = Collections.synchronizedSet(new HashSet<HttpSession>());
	static Map<String, Player> players = new HashMap<String, Player>();
	static Map<String, Session> conections = new HashMap<String, Session>();
	
	private byte[] img_buffer = null;
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		httpSession.setAttribute("wsSession", session);
		Player player = (Player) httpSession.getAttribute("player");
		System.out.println("OPEN WS");
		players.put(session.getId(), player);
		conections.put(session.getId(), session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			JSONObject jso = new JSONObject(message);
			if(jso.get("TYPE").equals("chat")) {
				System.out.println("[" + jso.get("userName") + "] : " + jso.get("message") + "\n");
				/**** TEST ***/
				JSONObject jsoSent = new JSONObject();
				jsoSent.put("TYPE", "chatIncome");
				jsoSent.put("userName", jso.get("userName"));
				jsoSent.put("message", jso.get("message"));
				
				Collection<Session> sessions_collection = conections.values();
				Iterator<Session> it = sessions_collection.iterator();
				while(it.hasNext()) {
					Session s = it.next();
					s.getBasicRemote().sendText(jsoSent.toString());
				}
				/**************/
			}
		}catch (Exception e) {
			players.remove(session.getId());
			conections.remove(session.getId());
		}
	}
	
	@OnMessage
	public void onBinaryMessage(byte[] message, boolean last, Session session) {
		try {
			//System.out.println("BINARY FOTO!!!");
			Player player = players.get(session.getId());
			//System.out.println(player.getEmail());
			player.setPhotoBinary(message);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//img_buffer = null;
		}
		/*byte[] aux_buffer = new byte[0];
		if(img_buffer != null) {
			aux_buffer = img_buffer.clone();
		}
		img_buffer = new byte[aux_buffer.length+message.length];
		for(int i = 0;i<aux_buffer.length; i++) {
			img_buffer[i] = aux_buffer[i];
		}
		for(int i = 0; i<message.length; i++) {
			img_buffer[aux_buffer.length+i] = message[i]; 
		}
		
		if(last) {
			try {
				System.out.println("BINARY FOTO!!!");
				Player player = players.get(session.getId());
				System.out.println(player.getEmail());
				player.setPhotoBinary(message);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				img_buffer = null;
			}
		}*/
	}
	
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("CLOSE WS");
		players.remove(session.getId());
		conections.remove(session.getId());
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
