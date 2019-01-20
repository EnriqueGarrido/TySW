/**
 * 
 */

function login(){
    var request = new XMLHttpRequest();
    request.open("post","servers/login.jsp");
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.withCredentials= true;
    request.onreadystatechange = function (){
        if(request.readyState === 4){
            var respuesta = JSON.parse(request.responseText);
            if(respuesta.result ==="OK"){	
            	
            	localStorage.nombre = respuesta.nombre;
            	localStorage.email = respuesta.email;
            	
            	location.href="GameSelection.html";
            }else{
            	location.href="RecoverPassword.html";
            }
        }
    };
    var p = {
            email:Nombre.value, pwd1:pass.value
    };
    request.send("p="+JSON.stringify(p));
    sleep(30000);

}