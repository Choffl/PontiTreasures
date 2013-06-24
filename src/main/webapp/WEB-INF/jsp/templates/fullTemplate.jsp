<!DOCTYPE HTML>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<html lang="es">

	<head>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<meta name="description" content="Practica asignatura">
		<meta name="keywords" content="moviles, upsam">
		
		<title>PontiTreasures web</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/baselayout.css"/>"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>"/>
	</head>

	<body>
		<tiles:insertAttribute name="contenido" />
	</body>

</html>