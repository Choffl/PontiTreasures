<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<script>
	$(document).ready(function() {
		$('input[type="button"]').bind('click', function(event) {
			$('.formDialog').css("display","none");
		});
		$('.accionAlta a').bind('click', function(event) {
			$('#circuitoForm').find("input[type=text], input[type=hidden], textarea").val("");
        	$('[name="etiquetas"]').val(0);
        	$('#circuitoForm').attr('action', 'circuitos/circuito');
        	$('.mensajes').css("display","none");
			$('.formDialog').css("display","block");	
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
    	$('[name="descripcion"]').val(data.descripcion);
    	$('input[name="id"]').val(data.id);
    	$('[name="etiquetas"]').val(data.etiquetas);
    	$('#circuitoForm').attr('action', 'circuitos/circuito?modify');
    	$('.mensajes').css("display","none");
    	$('.formDialog').css("display","block");
	}
</script>

<div id="circuitos">
	<ul class="listado">
		<c:forEach items="${circuitos}" var="circuito">
			<li class="elemento">
				<div class="item">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-road"></i>
							<s:url value="/circuitos/{id}" var="urlVerCircuito">
								<s:param name="id" value="${circuito.id}"/>
							</s:url>
							<a href="${urlVerCircuito}">${circuito.nombre}</a>
						</h2>
					</hgroup>
					<ul class="listado-acciones">
						<li class="ver">
							<a href="${urlVerCircuito}"><i class="icon-eye-open icon-2x"></i></a>
						</li>
						<li class="modificar">
							<s:url value="/circuitos/{id}" var="urlModificarCircuito">
								<s:param name="id" value="${circuito.id}"/>
							</s:url>
							<a href="${urlModificarCircuito}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<li class="eliminar">
							<s:url value="/circuitos/{id}" var="urlEliminarCircuito">
								<s:param name="id" value="${circuito.id}"/>
								<s:param name="action" value="delete"/>
							</s:url>
							<a href="${urlEliminarCircuito}"><i class="icon-trash icon-2x"></i></a>
						</li>
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
		<sf:form id="circuitoForm" methodParam="POST" modelAttribute="circuitoForm" action="circuitos/circuito">
			<h2>Registra circuito</h2>
			<sf:hidden path="id"/>
			<fieldset>
				<div class="field full">
					<label for="nombre">Nombre</label>
					<sf:input path="nombre" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field full">
					<label for="descripcion">Descripcion</label>
					<sf:textarea path="descripcion" spellcheck="false" cssClass="text noresize"/>
				</div>
				<div class="field full">
					<label for="etiquetas">Etiquetas</label>
					<sf:select path="etiquetas" multiple="true">
						<sf:options items="${etiquetas}" itemLabel="codigo" itemValue="id"/>
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