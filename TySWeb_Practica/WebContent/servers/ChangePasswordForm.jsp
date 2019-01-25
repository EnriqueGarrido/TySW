<%@page import="edu.uclm.esi.tysweb.games.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<title>ChangePassword - TCG</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<div class="login-page">
  	<div class="form">
  	<h1>Change Password Service</h1>
  	<form class="recover-form">
      	<input type="text" placeholder="New password"/>
      	<input type="text" placeholder="Confirm new password"/>
      	<button>Change password</button>
    </form>
  </div>
</div>


</body>
</html>

<%
	String token_str = request.getParameter("token");
	Token token = new Token();
	token.setValue(token_str);
	try{
		String email = token.getAssociatedEmail();
		
	}catch(Exception e){
		
	}
%>