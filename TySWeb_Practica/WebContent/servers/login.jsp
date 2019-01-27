<%@page import="edu.uclm.esi.tysweb.games.Player"%>
<%@page import="edu.uclm.esi.tysweb.web.Manager"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"%>

<%
	String p = request.getParameter("p");
	JSONObject jso=new JSONObject(p);	
	JSONObject respuesta=new JSONObject();
	
	

	try {
		String email=jso.optString("email");
		String pwd=jso.optString("pwd1");
	
		//Player usuario = new Player();
		//usuario.setUserName("ana");
		//usuario.setPassword("ana123");
		Player player = Manager.get().login(email, pwd);
		session.setAttribute("player", player);
		
		Cookie cookieUserName=new Cookie("playerName", player.getUserName());
		//cookieUserName.setMaxAge(3000000);
		cookieUserName.setPath("/");
		response.addCookie(cookieUserName);
		
		if(player == null)
			throw new Exception();
		
		respuesta.put("result", "OK");
		respuesta.put("nombre", player.getUserName());
		respuesta.put("email", player.getEmail());
		respuesta.put("photo", player.getPhotoStr());
	}
	catch (Exception e) {
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}
	out.println(respuesta.toString());
%>