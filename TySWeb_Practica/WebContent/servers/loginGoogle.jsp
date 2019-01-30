<%@page import="edu.uclm.esi.tysweb.games.*"%>
<%@page import="edu.uclm.esi.tysweb.web.*"%>
<%@page import="org.json.*"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"%>

<%
	String p = request.getParameter("p");
	JSONObject jso=new JSONObject(p);	
	JSONObject respuesta=new JSONObject();
	
	try{
		String idGoogle = jso.optString("idGoogle");
		String name = jso.optString("name");
		String email = jso.optString("email");
		
		Player player = Manager.get().loginGoogle(idGoogle, name, email);
		session.setAttribute("player", player);
		if(player == null)
			throw new Exception("Error loging in with Google");
		
		respuesta.put("result", "OK");
		respuesta.put("nombre", player.getUserName());
		respuesta.put("email", player.getEmail());
		
	}catch (Exception e) {
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}
	out.println(respuesta.toString());
%>
