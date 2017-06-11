
/**
 * 
 */
var ws;
var stompClient;
window.onload = function(){
	var username = /*[[${user.email}]]*/ 'Sebastian';
	ws = new SockJS("/StartupWeb/chat");
	stompClient = Stomp.over(ws);
	stompClient.connect({}, function(frame){
		stompClient.subscribe("/topic/mensajes", function(mensajeJSON){
			var mensaje = mensajeJSON.body.split("::");
			console.log("Received:" + mensaje[1]);			
			document.getElementById("messages").innerHTML += "<div class='row message-bubble'><p class='text-muted'>"+ mensaje[0] +"</p><span>" + mensaje[1] + "</span></div>";
		});
	}, function(error){
		console.log("STOMP protocol error " + error);
	});
	document.getElementById("sendButton").onclick = enviarMensaje;
};

function enviarMensaje(){

	if(document.getElementById("inputText").value != null || document.getElementById("inputText").value != "" ){
		var mensaje = "::" +$("#destinatario").text() + "::" + $("#user").text() + "::" +document.getElementById("inputText").value+ "::";
		stompClient.send("/app/chat",{}, JSON.stringify(mensaje));
		document.getElementById("inputText").value = "";
	}
	
}
