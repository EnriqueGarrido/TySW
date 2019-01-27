window.onload = function() {
	var user_name_text = document.getElementById("welcome-msg");
	//user_name_text.innerHTML = "Welcome, " + localStorage.name;
	user_name_text.innerHTML = "Welcome, " + sessionStorage.name;

	createWebSocket();
}

function Send() {
	var text_to_send = document.getElementById("usermsg");
	var p = {
		TYPE : "chat",
		//userName : localStorage.name,
		userName : sessionStorage.name,
		message : text_to_send.value
	};
	ws.send(JSON.stringify(p));
	text_to_send.value = "";
}

var ws = {};
function createWebSocket() {
	var url = "ws://" + window.location.hostname + ":" + window.location.port
			+ "/TySWeb_Practica/ws_server";

	ws = new WebSocket(url);

	ws.onopen = function() {
	}

	ws.onerror = function() {
	}

	ws.onclose = function() {
	}

	ws.onmessage = function(m) {
		var msg = JSON.parse(m.data);

		//if (m.TYPE == "chatIncome") {
			var conversation = document.getElementById("all-conver");

			var userName = msg.userName;
			var message = msg.message;

			conversation.innerHTML += "[" + userName + "] : " + message + "<br>";
		//}
	}
}

window.onbeforeunload = function() {
	ws.close();
}