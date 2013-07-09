<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>



<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	


<div id="caza">
	<article>
		<div id="info">
			<p class="dataInfo">
				<span class="tituloInfo">Nombre:</span>
				<span class="contenidoInfo">${caza.nombre}</span>
			</p>

			<p>
				<span class="tituloInfo">Premio para anonimos:</span>
				<span class="contenidoInfo">${caza.paginaPremioAnonimo.nombre}</span>
			</p>
			<p>
				<span class="tituloInfo">Premio para identificados:</span>
				<span class="contenidoInfo">${caza.paginaPremioIdentificado.nombre}</span>
			</p>
			<p>
				<span class="tituloInfo">Circuito</span>
				<span class="contenidoInfo">${caza.circuito.nombre}</span>
			</p>
			<p>
				<span class="tituloInfo">Gestor</span>
				<span class="contenidoInfo">${caza.gestor.username}</span>
			</p>
			<p>
				<span class="tituloInfo">Numero de checkins para premio</span>
				<span class="contenidoInfo">${caza.numeroCheckinPremio}</span>
			</p>
			<p>
				<span class="tituloInfo">Numero de checkins disntintos para premio</span>
				<span class="contenidoInfo">${caza.numeroDistintoCheckinPremio}</span>
			</p>
		</div>
	</article>
</div>