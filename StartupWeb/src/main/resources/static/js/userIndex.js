var ws;
var stompClient;

$(function() {
	ws = new SockJS("/StartupWeb/chat");
	stompClient = Stomp.over(ws);
	stompClient.connect({}, function(frame){
		stompClient.subscribe("/topic/mensajes", function(mensajeJSON){
			var mensaje = mensajeJSON.body.split("::");
			console.log("Received:" + mensaje[1]);			
			document.getElementById("mensajes").innerHTML += "<div id=\"listMensajes\"><a th:href=\"@{/Mensajes/{idDestinatario}(idDestinatario=${"+mensaje[2]+"})}\"><div class='message'><p class='text-muted'>"+ mensaje[0] +"</p><span>" + mensaje[1] + "</span></div></a></div>";
		});
	}, function(error){
		console.log("STOMP protocol error " + error);
	});
	
	var $ppc = $('.progress-pie-chart'), percent = parseInt(100), deg = 360;
	if (percent > 50) {
		$ppc.addClass('gt-50');
	}
	$('.ppc-progress-fill').css('transform', 'rotate(' + deg + 'deg)');
	$('.ppc-percents span').html(percent + '%');
	
	$('.count').each(function() {
		$(this).prop('Counter', 0).animate({
			Counter : $(this).text()
		}, {
			duration : 20,
			easing : 'swing',
			step : function(now) {
				$(this).text(Math.ceil(now));
			}
		});
	});

	jQuery('.skillbar').each(function() {
		jQuery(this).find('.skillbar-bar').animate({
			width : jQuery(this).attr('data-percent')
		}, 2000);
	});

	$("#imgPerfil").on("mouseenter", function() {
		$("#popupEditaImagen").show();
	}).on("mouseleave", function() {
		$("#popupEditaImagen").hide();
	});
	$("#popupEditaImagen").on("mouseenter", function() {
		$("#popupEditaImagen").show();
	}).on("mouseleave", function() {
		$("#popupEditaImagen").hide();
	});

});


function enviarMensaje(){

	if(document.getElementById("inputText").value != null || document.getElementById("inputText").value != "" ){
		var mensaje = "::" +$("#destinatario").text() + "::" + $("#user").text() + "::" +document.getElementById("inputText").value+ "::";
		stompClient.send("/app/chat",{}, JSON.stringify(mensaje));
		document.getElementById("inputText").value = "";
	}
	
}