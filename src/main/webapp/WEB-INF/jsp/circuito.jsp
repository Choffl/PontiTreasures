<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>



<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	


<div id="circuito">
	<article>
		<div id="info">
			<p class="dataInfo">
				<span class="tituloInfo">Nombre:</span>
				<span class="contenidoInfo">${circuito.nombre}</span>
			</p>

			<p>
				<span class="tituloInfo">Descripcion:</span>
				<span class="contenidoInfo">${circuito.descripcion}</span>
			</p>
		</div>
	</article>
</div>