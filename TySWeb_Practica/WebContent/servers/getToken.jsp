<%@page import="edu.uclm.esi.tysweb.games.*"%>
<%@page import="edu.uclm.esi.tysweb.web.*"%>
<%@page import="org.json.*"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"%>

<%

	String p = request.getParameter("p");
	JSONObject jso =  new JSONObject(p);
	
	JSONObject respuesta = new JSONObject();
	try{
		String email = jso.optString("email");
		Player.requestToken(email);
		
		
	}catch(Exception e){
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}


%>