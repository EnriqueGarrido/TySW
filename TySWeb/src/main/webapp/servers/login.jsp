<%@page import="edu.uclm.esi.games.*"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%
	String p = request.getParameter("p");
	JSONObject jso=new JSONObject(p);	
	JSONObject respuesta=new JSONObject();
	
	

	try {
		String email=jso.optString("email");
		String pwd=jso.optString("pwd1");
	
		Player usuario = new Player();
		usuario.setUserName("ana");
		usuario.setPwd("ana123");
		//Player usuario = Manager.get().login(email, pwd);
		//session.setAttribute("usuario", usuario);
		
		//Cookie cookiePWD=new Cookie("pwd", pwd);
		//cookiePWD.setMaxAge(3000000);
		//response.addCookie(cookiePWD);
		
		if(usuario == null)
			throw new Exception();
		
		respuesta.put("result", "OK");
		respuesta.put("nombre", usuario.getUserName());
		//respuesta.put("email", usuario.getEmail());
		//respuesta.put("photo", usuario.getPhoto());
	}
	catch (Exception e) {
		respuesta.put("result", "ERROR");
		respuesta.put("mensaje", e.getMessage());
	}
	out.println(respuesta.toString());
%>