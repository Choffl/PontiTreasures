<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">


<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	
		
<script src="<c:url value="/resources/js/Chart.min.js"/>"></script>		
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>


<div id="etiquetaGraficas">
	<article>
		<div id="graficas">
			<canvas id="canvas" width="600" height="400"></canvas>
			<p id="leyenda">
				<span id="identificadosColor">Identificados</span>
				<span id="anonimosColor">Anonimos</span>
			</p>
			<p class="dataInfo">
				<span class="tituloInfo">Identificados:</span>
				<span id="numeroCheckinsIdentificados" class="contenidoInfo">${numeroCheckinsIdentificados}</span>
			</p>
			<p class="dataInfo">
				<span class="tituloInfo">Anonimos:</span>
				<span id="numeroCheckinsAnonimos" class="contenidoInfo">${numeroCheckinsAnonimos}</span>
			</p>
			<p class="novisible">
				<span class="tituloInfo">Codigo:</span>
				<span id="codigo" class="contenidoInfo">${etiqueta.codigo}</span>
			</p>
			<p class="novisible">
				<span class="tituloInfo">Latitud:</span>
				<span id="latitud" class="contenidoInfo">${etiqueta.latitud}</span>
			</p>
			<p class="novisible">
				<span class="tituloInfo">Longitud:</span>
				<span id="longitud" class="contenidoInfo">${etiqueta.longitud}</span>
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
	
	var data = {
		labels : ["Nº Checkins"],
		datasets : [
			{
				fillColor : "rgba(255,205,70,0.5)",
				strokeColor : "rgba(255,205,70,1)",
				data : ['${numeroCheckinsAnonimos}']
			},
			{
				fillColor : "rgba(255,112,70,0.5)",
				strokeColor : "rgba(255,112,70,1)",
				data : ['${numeroCheckinsIdentificados}']
			}
		]
	};
	
	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(data, options);
</script>