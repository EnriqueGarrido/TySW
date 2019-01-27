/**
 * 
 */

function login(){
    var request = new XMLHttpRequest();
    request.open("post","servers/login.jsp");
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.withCredentials= true;
    request.onreadystatechange = function (){
        if(request.readyState == 4){
            var respuesta = JSON.parse(request.responseText);
            if(respuesta.result =="OK"){	
            	
            	//localStorage.name = respuesta.nombre;
            	//localStorage.email = respuesta.email;
            	//localStorage.photo = respuesta.photo;
            	
            	sessionStorage.name = respuesta.nombre;
            	sessionStorage.email = respuesta.email;
            	sessionStorage.photo = respuesta.photo;
            	
            	location.href="GameSelection.html";
            }else{
            	var error_text = document.getElementById("login-warning-message");
            	var pwd_text = document.getElementById("pass");
            	error_text.innerHTML = "Error loging in.";
            	error_text.setAttribute("style", "display:block");
            	pwd_text.value = "";
            }
        }
    };
    var p = {
            email:email.value, pwd1:pass.value
    };
    request.send("p="+JSON.stringify(p));
}

function register(){
	var request = new XMLHttpRequest();
	request.open("post", "servers/register.jsp");
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange=function(){
		if(request.readyState == 4) {
			var response = JSON.parse(request.responseText);
			if(response.result == "OK"){
				location.href = "index.html";
				var error_text = document.getElementById("login-warning-message");
				error_text.innerHTML = "Login Successful. Please, login to continue.";
            	error_text.setAttribute("style", "display:block");
            	error_text.setAttribute("style", "color:#4CAF50");
			}else{
				var error_text = document.getElementById("register-warning-message");
				error_text.innerHTML = response.mensaje;
				error_text.setAttribute("style", "display:block");
			}
		}
	}
	var p = {
		email: register_email.value,
		name: register_name.value,
		pwd1: register_pwd.value,
		pwd2: register_pwd1.value
	};
	request.send("p="+JSON.stringify(p));
}

/*Called when Loging in with google*/
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	registerOrLogIn(profile.getId(), profile.getName(), profile.getEmail());
}

function registerOrLogIn(idGoogle, name, email){
	var request = new XMLHttpRequest();
	request.open("POST", "servers/loginGoogle.jsp");
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange=function(){
		if(request.readyState == 4){
            var respuesta = JSON.parse(request.responseText);
            if(respuesta.result =="OK"){	
            	sessionStorage.name = respuesta.nombre;
            	sessionStorage.email = respuesta.email;
            	location.href="GameSelection.html";
            }
		}
	}
	//var p = "idGoogle=" + idGoogle + "&nombre=" + nombre + "&email=" + email;
	var p = {
			idGoogle: idGoogle,
			name: name,
			email: email 
	};
	request.send("p="+JSON.stringify(p));
}

/* DEBERIA IR DONDE SE VAYA A SUBIR LA FOTO*/
function uploadPhoto(){
	
}