<%@page import="edu.uclm.esi.tysweb.games.*"%>
<%@page import="edu.uclm.esi.tysweb.web.*"%>
<%@page import="org.json.*"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"%>

<%
	String p = request.getParameter("p");
	JSONObject jso=new JSONObject(p);	

	JSONObject respuesta=new JSONObject();
	try {
		String usuario = jso.optString("name");
		String email = jso.optString("email");
		String pwd1 = jso.optString("pwd1");
		String pwd2 = jso.optString("pwd2");
		
		
		checkPasswords(pwd1, pwd2);
		Manager.get().register(email, pwd1, usuario);
		respuesta.put("result", "OK");
	}
	catch (Exception e) {
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}
	out.println(respuesta.toString());
%>

<%!
private void checkPasswords(String pwd1, String pwd2) throws Exception {
	if (!pwd1.equals(pwd2))
		throw new Exception("Passwords are different.");
	if (pwd1.length()<4)
		throw new Exception("Password is too short.\nMust have at least by 4 characters");
}
%>