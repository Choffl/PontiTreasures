<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<script>
	$(document).ready(function() {
		$('input[type="button"]').bind('click', function(event) {
			$('.formDialog').css("display","none");
		});
		$('.accionAlta a').bind('click', function(event) {
			$('#etiquetaForm').find("input[type=text], input[type=hidden], textarea").val("");
        	$('[name="idPaginaAnonimo"]').val(0);
        	$('[name="idPaginaIdentificado"]').val(0);

        	$('#etiquetaForm').attr('action', 'etiquetas/etiqueta');
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
    	$('input[name="codigo"]').val(data.codigo);
    	$('[name="descripcion"]').val(data.descripcion);
    	$('input[name="longitud"]').val(data.longitud);
    	$('input[name="latitud"]').val(data.latitud);
    	$('input[name="id"]').val(data.id);
    	$('[name="idPaginaAnonimo"]').val(data.idPaginaAnonimo);
    	$('[name="idPaginaIdentificado"]').val(data.idPaginaIdentificado);
    	$('#etiquetaForm').attr('action', 'etiquetas/etiqueta?modify');
    	$('.mensajes').css("display","none");
    	$('.formDialog').css("display","block");
	}
</script>

<div id="etiquetas">
	<ul class="listado">
		<c:forEach items="${etiquetas}" var="etiqueta">
			<li class="elemento">
				<div class="item">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-qrcode"></i>
							<s:url value="/etiquetas/{id}" var="urlVerEtiqueta">
								<s:param name="id" value="${etiqueta.id}"/>
							</s:url>
							<a href="${urlVerEtiqueta}">${etiqueta.codigo}</a>
						</h2>
					</hgroup>
					<ul class="listado-acciones">
						<li class="ver">
							<a href="${urlVerEtiqueta}"><i class="icon-eye-open icon-2x"></i></a>
						</li>
						<li class="mostrarGrafica">
							<s:url value="/etiquetas/{id}" var="urlMostrarGraficas">
								<s:param name="id" value="${etiqueta.id}"/>
								<s:param name="action" value="graphs"/>
							</s:url>
							<a href="${urlMostrarGraficas}"><i class="icon-bar-chart icon-2x"></i></a>
						</li>
						<li class="modificar">
							<s:url value="/etiquetas/{id}" var="urlModificarEtiqueta">
								<s:param name="id" value="${etiqueta.id}"/>
							</s:url>
							<a href="${urlModificarEtiqueta}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<li class="eliminar">
							<s:url value="/etiquetas/{id}" var="urlEliminarEtiqueta">
								<s:param name="id" value="${etiqueta.id}"/>
								<s:param name="action" value="delete"/>
							</s:url>
							<a href="${urlEliminarEtiqueta}"><i class="icon-trash icon-2x"></i></a>
						</li>
					</ul>		
				</div>			
			</li>
		</c:forEach>
	</ul>
</div>
<div class="accionAlta">
	<i class="icon-plus icon-2x"></i>
	<a href="#">Alta</a>
</div>
<div class="mensajes">
	${successMsg}
</div>
<div id="formularioAlta" class="formDialog">
	<div class="dialog">
		<sf:form id="etiquetaForm" methodParam="POST" modelAttribute="etiquetaForm" action="etiquetas/etiqueta">
			<h2>Registra etiqueta</h2>
			<sf:hidden path="id"/>
			<fieldset>
				<div class="field full">
					<label for="codigo">Codigo</label>
					<sf:input path="codigo" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field full">
					<label for="descripcion">Descripcion</label>
					<sf:textarea path="descripcion" spellcheck="false" cssClass="text noresize"/>
				</div>
				<div class="field multi">
					<label for="longitud">Longitud</label>
					<sf:input path="longitud" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="latitud">Latitud</label>
					<sf:input path="latitud" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="idPaginaAnonimo">Pagina checkin anonimo</label>
					<sf:select path="idPaginaAnonimo">
						<sf:option value="0" label="Escoge..." />
						<sf:options items="${paginas}" itemLabel="nombre" itemValue="id"/>
					</sf:select>
				</div>
				<div class="field multi">
					<label for="idPaginaIdentificado">Pagina checkin registrado</label>
					<sf:select path="idPaginaIdentificado">
						<sf:option value="0" label="Escoge..." />
						<sf:options items="${paginas}" itemLabel="nombre" itemValue="id"/>
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