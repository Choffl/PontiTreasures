<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>



<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	


<div id="caza">
	<article>
		<div id="info">
			<p class="dataInfo">
				<span class="tituloInfo">Codigo:</span>
				<span class="contenidoInfo">${caza.codigo}</span>
			</p>
			<p>
				<span class="tituloInfo">Descripcion:</span>
				<span class="contenidoInfo">${caza.descripcion}</span>
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
				<span id="latitud" class="contenidoInfo">${caza.latitud}</span>
			</p>
			<p class="novisible">
				<span class="tituloInfo">Longitud:</span>
				<span id="longitud" class="contenidoInfo">${caza.longitud}</span>
			</p>

		</div>
	</article>
</div>