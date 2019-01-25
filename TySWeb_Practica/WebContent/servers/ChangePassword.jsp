<%@page import="edu.uclm.esi.tysweb.games.*"%>
<%@page import="edu.uclm.esi.tysweb.web.*"%>
<%@page import="org.json.*"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"%>

<%
	String p = request.getParameter("p");
	JSONObject jso =  new JSONObject(p);

	JSONObject respuesta = new JSONObject();

	String pwd1 = jso.optString("pwd1");
	String pwd2 = jso.optString("pwd2");
	String token_str = jso.optString("token");
	
	Token token = new Token();
	token.setValue(token_str);
	
	try {
		String email = token.getAssociatedEmail();
		Player player = new Player();
		player.setEmail(email);
		player.changePassword(token, pwd1, pwd2);
		respuesta.put("result", "OK");
		respuesta.put("mensaje", "Password Changed");

	} catch (Exception e) {
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}
	out.println(respuesta.toString());
%>