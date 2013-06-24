<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>



<link rel="stylesheet" href="<c:url value="/resources/css/detaillayout.css"/>">	


<div id="caza">
	<article>
		<div id="info">
			<p class="dataInfo">
				<span class="tituloInfo">Identificador:</span>
				<span class="contenidoInfo">${pagina.nombre}</span>
			</p>
			<p>
				<span class="tituloInfo">Descripcion:</span>
				<span class="contenidoInfo">${pagina.descripcion}</span>
			</p>
			<s:url value="/paginas/{id}/html" var="urlPaginaHTML">
				<s:param name="id" value="${pagina.id}"/>
			</s:url>
		</div>
		<div id="htmljQuery">
			<p class="dataInfo">
				<span class="tituloInfo">Contenido pagina:</span>
			</p>
			<s:url value="/paginas/{id}/html" var="urlPaginaHTML">
				<s:param name="id" value="${pagina.id}"/>
			</s:url>
			<iframe src="${urlPaginaHTML}" name="htmlPaginajQuery"></iframe>
			<s:url value="/paginas/{id}/html" var="urlDescargaPaginaHTML">
				<s:param name="id" value="${pagina.id}"/>
				<s:param name="download" value="yes"/>
			</s:url>
			<a href="${urlDescargaPaginaHTML}">Descargar fichero</a>
		</div>
	</article>
</div>