<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">


<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	
		
<script src="<c:url value="/resources/js/Chart.min.js"/>"></script>		


<div id="cazaGraficas">
	<article>
		<div id="graficas">
			<article>
				<canvas id="canvasCheckins" width="300" height="400"></canvas>
				<canvas id="canvasPremios" width="300" height="400"></canvas>
				<p id="leyenda">
					<span id="identificadosColor">Identificados</span>
					<span id="anonimosColor">Anonimos</span>
				</p>	
				<p class="dataInfo">
					<span class="tituloInfo">Checkins anonimos:</span>
					<span id="numeroCheckinsAnonimos" class="contenidoInfo">${numeroCheckinsAnonimos}</span>
				</p>
				<p class="dataInfo">
					<span class="tituloInfo">Checkins identificados:</span>
					<span id="numeroCheckinsIdentificados" class="contenidoInfo">${numeroCheckinsIdentificados}</span>
				</p>
				<p class="dataInfo">
					<span class="tituloInfo">Premios anonimos:</span>
					<span id="numeroCheckinsAnonimos" class="contenidoInfo">${numeroPremiosAnonimos}</span>
				</p>
				<p class="dataInfo">
					<span class="tituloInfo">Premios identificados:</span>
					<span id="numeroCheckinsIdentificados" class="contenidoInfo">${numeroPremiosIdentificados}</span>
				</p>
			</article>
		</div>	
	</article>
</div>

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
				data : ['${numeroCheckinsAnonimos}']
			},
			{
				fillColor : "rgba(255,112,70,0.5)",
				strokeColor : "rgba(255,112,70,1)",
				data : ['${numeroCheckinsIdentificados}']
			}
		]
	};
	
	var dataPremios = {
			labels : ["Nº Premios"],
			datasets : [
				{
					fillColor : "rgba(255,205,70,0.5)",
					strokeColor : "rgba(255,205,70,1)",
					data : ['${numeroPremiosAnonimos}']
				},
				{
					fillColor : "rgba(255,112,70,0.5)",
					strokeColor : "rgba(255,112,70,1)",
					data : ['${numeroPremiosIdentificados}']
				}
			]
		};
	
	var myLine = new Chart(document.getElementById("canvasCheckins").getContext("2d")).Bar(dataCheckins, options);
	var myLine = new Chart(document.getElementById("canvasPremios").getContext("2d")).Bar(dataPremios, options);
</script>