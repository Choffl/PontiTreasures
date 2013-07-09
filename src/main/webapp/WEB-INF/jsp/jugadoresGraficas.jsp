<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">


<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	
		
<script src="<c:url value="/resources/js/Chart.min.js"/>"></script>		
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>


<div id="jugadoresGraficas">
	<article>
		<div id="graficas">
			<canvas id="canvasCheckins" width="300" height="400"></canvas>
			<canvas id="canvasPremios" width="300" height="400"></canvas>
			<p class="dataInfo">
				<span class="tituloInfo">Checkins:</span>
				<span id="numeroCheckinsIdentificados" class="contenidoInfo">${numeroCheckins}</span>
			</p>
			<p class="dataInfo">
				<span class="tituloInfo">Premios:</span>
				<span id="numeroCheckinsAnonimos" class="contenidoInfo">${numeroPremios}</span>
			</p>

		</div>

		<div id="mapa" style="height:350px">
		</div>	
	</article>
</div>

<script>	
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 18,
	    center: new google.maps.LatLng(document.getElementById('latitud').innerHTML, document.getElementById('longitud').innerHTML),
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  };
	  map = new google.maps.Map(document.getElementById('mapa'), mapOptions);
	  
		var marker = new google.maps.Marker({
		      position: new google.maps.LatLng(document.getElementById('latitud').innerHTML, document.getElementById('longitud').innerHTML),
		      map: map,
		      title: document.getElementById('codigo').innerHTML
		});
	}

	google.maps.event.addDomListener(window, 'load', initialize);
	
</script>

<script>
var options = {	
		scaleOverlay :true,
		scaleOverride :true,
		scaleSteps : 30,
		scaleStepWidth : 1,
		scaleStartValue : 0,
		scaleLineColor : "rgba(0,0,0,.1)",	
		scaleLineWidth : 1,
		scaleFontFamily : "'Arial'",
		scaleFontSize : 12,
		scaleFontStyle : "normal",
		scaleFontColor : "#666",	
		scaleShowGridLines : true,
		scaleGridLineColor : "rgba(0,0,0,.05)",
		scaleGridLineWidth : 1,	
		barShowStroke : true,
		barStrokeWidth : 2,
		barValueSpacing : 5,
		barDatasetSpacing : 1,
		animation : true,
		animationSteps : 60,
		animationEasing : "easeOutQuart",
		onAnimationComplete : null		
	};
	
	var dataCheckins = {
		labels : ["Nº Checkins"],
		datasets : [
			{
				fillColor : "rgba(255,205,70,0.5)",
				strokeColor : "rgba(255,205,70,1)",
				data : ['${numeroCheckins}']
			}
		]
	};
	
	var dataPremios = {
			labels : ["Nº Premios"],
			datasets : [
				{
					fillColor : "rgba(255,205,70,0.5)",
					strokeColor : "rgba(255,205,70,1)",
					data : ['${numeroPremios}']
				}
			]
		};
	
	var myLine = new Chart(document.getElementById("canvasCheckins").getContext("2d")).Bar(dataCheckins, options);
	var myLine = new Chart(document.getElementById("canvasPremios").getContext("2d")).Bar(dataPremios, options);
</script>