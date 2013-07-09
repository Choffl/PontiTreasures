<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<script>
	$(document).ready(function() {
		$('input[type="button"]').bind('click', function(event) {
			$('.formDialog').css("display","none");
		});
		$('.accionAlta a').bind('click', function(event) {
			$('#cazaForm').find("input[type=text], input[type=hidden], textarea").val("");
        	$('[name="idPaginaPremioAnonimo"]').val(0);
        	$('[name="idPaginaPremioIdentificado"]').val(0);
        	$('[name="gestorId"]').val(0);
        	$('[name="circuitoId"]').val(0);
        	$('#cazaForm').attr('action', 'cazas/caza');
        	$('.mensajes').css("display","none");
			$('.formDialog').css("display","block");	
		});
		$("#fechaInicio").datepicker({
		      changeMonth: true,
		      changeYear: true
		});
		$("#fechaFin").datepicker({
		      changeMonth: true,
		      changeYear: true
		});
		$('.modificar a').bind('click', function(event) {
			cargaDatos($(this).attr('href'));
			return false;
			
		});
		
		if($.trim($(".mensajes").html())==''){
			$('.mensajes').css("display","none");
		}else{
			$('.mensajes').css("display","block");
		}
	});
	
	function cargaDatos(url){
		$.ajax({
	        url: url,
	        type: "GET",
	        dataType: 'json',
	        success: function (data) {
	        	prepararFormModificacion(data);
	        }
	    });
	}
	
	function prepararFormModificacion(data){
    	$('input[name="nombre"]').val(data.nombre);
    	$('input[name="premio"]').val(data.premio);
    	$('input[name="distintoPremio"]').val(data.distintoPremio);
    	$('input[name="id"]').val(data.id);
    	$('[name="idPaginaPremioAnonimo"]').val(data.idPaginaPremioAnonimo);
    	$('[name="idPaginaPremioIdentificado"]').val(data.idPaginaPremioIdentificado);
    	$('[name="gestorId"]').val(data.gestorId);
    	$('[name="circuitoId"]').val(data.circuitoId);
    	$('#cazaForm').attr('action', 'cazas/caza?modify');
    	$('.mensajes').css("display","none");
    	$('.formDialog').css("display","block");
	}
	
</script>

<div id="cazas">
	<ul class="listado">
		<c:forEach items="${cazas}" var="caza">
			<li class="elemento">
				<div class=item>
					<hgroup class="nombreElemento">
						<h2 class="element">
							<i class="icon-gamepad"></i>
							<s:url value="/cazas/{id}" var="urlVerCaza">
								<s:param name="id" value="${caza.id}"/>
							</s:url>
							<a href="${urlVerCaza}">${caza.nombre}</a>
						</h2>
					</hgroup>
					<ul class="listado-acciones">
						<li class="ver">
							<a href="${urlVerCaza}"><i class="icon-eye-open icon-2x"></i></a>
						</li>
						<li class="mostrarGrafica">
							<s:url value="/cazas/{id}" var="urlMostrarGraficas">
								<s:param name="id" value="${caza.id}"/>
								<s:param name="action" value="graphs"/>
							</s:url>
							<a href="${urlMostrarGraficas}"><i class="icon-bar-chart icon-2x"></i></a>
						</li>
						<li class="modificar">
							<s:url value="/cazas/{id}" var="urlModificarCaza">
								<s:param name="id" value="${caza.id}"/>
							</s:url>
							<a href="${urlModificarCaza}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<c:if test="${caza.estadoCaza eq 'CERRADO'}">
							<li class="eliminar">
								<s:url value="/cazas/{id}" var="urlEliminarCaza">
									<s:param name="id" value="${caza.id}"/>
									<s:param name="action" value="delete"/>
								</s:url>
								<a href="${urlEliminarCaza}"><i class="icon-trash icon-2x"></i></a>
							</li>
						</c:if>
						<c:if test="${caza.estadoCaza eq 'NUEVA'}">
							<li class="activar">
								<s:url value="/cazas/{id}" var="urlActivarCaza">
									<s:param name="id" value="${caza.id}"/>
									<s:param name="action" value="active"/>
								</s:url>
								<a href="${urlActivarCaza}"><i class="icon-unlock-alt icon-2x"></i></a>
							</li>	
						</c:if>
						<c:if test="${caza.estadoCaza eq 'ACTIVA' }">
							<li class="cerrar">
								<s:url value="/cazas/{id}" var="urlCerrarCaza">
									<s:param name="id" value="${caza.id}"/>
									<s:param name="action" value="close"/>
								</s:url>
								<a href="${urlCerrarCaza}"><i class="icon-lock icon-2x"></i></a>
							</li>	
						</c:if>
					</ul>		
				</div>			
			</li>
		</c:forEach>
	</ul>
</div>
<div class="mensajes">
	${successMsg}
</div>
<div class="accionAlta">
	<i class="icon-plus icon-2x"></i>
	<a href="#">Alta</a>
</div>
<div id="formularioAlta" class="formDialog">
	<div class="dialog">
		<sf:form id="cazaForm" methodParam="POST" modelAttribute="cazaForm" action="cazas/caza?">
			<h2>Comienza el juego</h2>
			<sf:hidden path="id"/>
			<fieldset>
				<div class="field full">
					<label for="nombre">Nombre</label>
					<sf:input path="nombre" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="idPaginaPremioAnonimo">Pagina premio anonimo</label>
					<sf:select path="idPaginaPremioAnonimo">
						<sf:option value="0" label="Escoge..."/>
						<sf:options items="${paginas}" itemLabel="nombre" itemValue="id"/>
					</sf:select>
				</div>
				<div class="field multi">
					<label for="idPaginaPremioIdentificado">Pagina premio registrado</label>
					<sf:select path="idPaginaPremioIdentificado">
						<sf:option value="0" label="Escoge..."/>
						<sf:options items="${paginas}" itemLabel="nombre" itemValue="id"/>
					</sf:select>
				</div>
				<div class="field multi">
					<label for="premio">Nº checkins para premio</label>
					<sf:input path="premio" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="distintoPremio">Nº checkins distintos para premio</label>
					<sf:input path="distintoPremio" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="gestor">Gestor</label>
					<sf:select path="gestorId">
						<sf:option value="0" label="Escoge..."/>
						<sf:options items="${gestores}" itemLabel="email" itemValue="id" cssClass="text"/>
					</sf:select>
				</div>
				<div class="field multi">
					<label for="circuitoId">Circuito</label>
					<sf:select path="circuitoId">
						<sf:option value="0" label="Escoge..."/>
						<sf:options items="${circuitos}" itemLabel="nombre" itemValue="id" cssClass="text"/>
					</sf:select>
				</div>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" value="Alta" name="commit" class="submit">
				<input type="button" value="Cancel" name="cancel" class="cancel">
			</fieldset>
		</sf:form>
	</div>
</div>