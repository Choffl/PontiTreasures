<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>



<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	


<div id="caza">
	<article>
		<div id="info">
			<p class="dataInfo">
				<span class="tituloInfo">Nombre jugador:</span>
				<span class="contenidoInfo">${jugador.username}</span>
			</p>
			<p>
				<span class="tituloInfo">Email:</span>
				<span class="contenidoInfo">${jugador.email}</span>
			</p>

		</div>
	</article>
</div>