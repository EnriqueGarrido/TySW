<%@page import="edu.uclm.esi.tysweb.games.Player"%>
<%@page import="edu.uclm.esi.tysweb.games.Match"%>

<%@page import="edu.uclm.esi.tysweb.games.GamesManager"%>

<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"%>

<%
	String p = request.getParameter("p");
	JSONObject jso = new JSONObject(p);
	JSONObject respuesta = new JSONObject();
	
	try{
		Player player = (Player) session.getAttribute("player");
		Match match = GamesManager.get().joinGame(player, "Rock, Paper, Scissors.");
		
		
		
	}catch(Exception e){
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}
	out.println(respuesta.toString());
%>