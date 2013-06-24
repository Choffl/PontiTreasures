<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>



<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	
		
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

<script>
	
	var map;
	function initialize() {
	  var mapOptions = {
	    zoom: 18,
	    center: new google.maps.LatLng(document.getElementById('latitud').innerHTML, document.getElementById('longitud').innerHTML),
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	  };
	  map = new google.maps.Map(document.getElementById('mapa'), mapOptions);
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>

<div id="etiqueta">
	<article>
		<div id="info">
			<p class="dataInfo">
				<span class="tituloInfo">Codigo:</span>
				<span class="contenidoInfo">${etiqueta.codigo}</span>
			</p>
			<p>
				<span class="tituloInfo">Descripcion:</span>
				<span class="contenidoInfo">${etiqueta.descripcion}</span>
			</p>
			<p>
				<span class="tituloInfo">Pista para anonimos:</span>
				<span class="contenidoInfo">Nombre la pagina jQuery para anonimos.</span>
			</p>
			<p>
				<span class="tituloInfo">Pista para identificados:</span>
				<span class="contenidoInfo">Nombre la pagina jQuery para identificados.</span>
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
		<div id="qr" class="imagen">
			<s:url value="/etiquetas/qr/{id}" var="urlQrcode">
				<s:param name="id" value="${etiqueta.id}"/>
			</s:url>
			<img alt="codigo qr" src="${urlQrcode}">
		</div>
		<div id="mapa" style="height: 400px;">
		</div>	
	</article>
</div>

