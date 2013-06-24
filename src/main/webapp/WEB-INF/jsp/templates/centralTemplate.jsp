<!DOCTYPE html>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html lang="es">

	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<meta name="description" content="Practica asignatura">
		<meta name="keywords" content="moviles, upsam">
		
		<title>PontiTreasures web</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/baselayout.css"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/mainlayout.css"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/gestionlayout.css"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/formlayout.css"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>">
		<link rel="stylesheet" href="<c:url value="/resources/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>">
		
		<script src="<c:url value="/resources/js/jquery-1.9.1.js"/>"/></script>
		<script src="<c:url value="/resources/js/jquery-ui-1.10.3.custom.min.js"/>"/></script>
	</head>

	<body>
		<div id="pagina">
			<header id="cabecera">
				<nav id="secciones">
					<ul>
						<s:url value="/etiquetas" var="urlEtiquetas"/>
						<li class="acciones"><a href="${urlEtiquetas}">Etiquetas</a></li>
						<s:url value="/paginas" var="urlPaginas"/>				
						<li class="acciones"><a href="${urlPaginas}">Paginas</a></li>
						<s:url value="/circuitos" var="urlCircuitos"/>							
						<li class="acciones"><a href="${urlCircuitos}">Circuitos</a></li>
						<s:url value="/gestores" var="urlGestores"/>							
						<li class="acciones"><a href="${urlGestores}">Gestores</a></li>
						<s:url value="/cazas" var="urlCazas"/>							
						<li class="acciones"><a href="${urlCazas}">Cazas</a></li>
					</ul>
				</nav>
				<nav id="aplicacion">
					<ul>
						<li class="acciones"><a href="#">Cuenta</a></li>
						<li class="acciones"><a href="#">Logout</a></li>

					</ul>
				</nav>
			</header>
			
			<section>
				<header class="tituloSeccion">
					<h1>${seccion}</h1>
				</header>
				<div id="contenidoSeccion">
					<tiles:insertAttribute name="contenido" />
				</div>
			</section>
			<footer id="pie">
				Derechos reservados &copy; 2012-2013
			</footer>
		</div>
	</body>

</html>