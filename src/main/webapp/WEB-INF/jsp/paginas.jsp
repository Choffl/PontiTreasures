<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<script>
	$(document).ready(function() {
		$('input[type="button"]').bind('click', function(event) {
			$('.formDialog').css("display","none");
		});
		$('.accionAlta a').bind('click', function(event) {
			$('#paginaForm').find("input[type=text], input[type=hidden], textarea").val("");
        	$('#paginaForm').attr('action', 'paginas/pagina');
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
    	$('#paginaForm').attr('action', 'paginas/pagina?modify');
    	$('.mensajes').css("display","none");
    	$('.formDialog').css("display","block");
	}
</script>

<div id="paginas">
	<ul class="listado">
		<c:forEach items="${paginas}" var="pagina">
			<li class="elemento">
				<div class="item">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-globe"></i>
							<s:url value="/paginas/{id}" var="urlVerPagina">
								<s:param name="id" value="${pagina.id}"/>
							</s:url>
							<a href="${urlVerPagina}">${pagina.nombre}</a>
						</h2>
					</hgroup>
					<ul class="listado-acciones">
						<li class="ver">
							<a href="${urlVerPagina}"><i class="icon-eye-open icon-2x"></i></a>
						</li>
						<li class="modificar">
							<s:url value="/paginas/{id}" var="urlModificarPagina">
								<s:param name="id" value="${pagina.id}"/>
							</s:url>
							<a href="${urlModificarPagina}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<c:if test="${pagina.tipoPagina eq 'PERSONALIZABLE'}">
							<li class="eliminar">
								<s:url value="/paginas/{id}" var="urlEliminarPagina">
									<s:param name="id" value="${pagina.id}"/>
									<s:param name="action" value="delete"/>
								</s:url>
								<a href="${urlEliminarPagina}"><i class="icon-trash icon-2x"></i></a>
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
		<sf:form id="paginaForm" methodParam="POST" modelAttribute="paginaForm" action="paginas/pagina" enctype="multipart/form-data">
			<h2>Registra pagina</h2>
			<sf:hidden path="id"/>
			<fieldset>
				<div class="field full">
					<label for="nombre">Identificador</label>
					<sf:input path="nombre" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field full">
					<label for="descripcion">Descripcion</label>
					<sf:textarea path="descripcion" spellcheck="false" cssClass="text noresize"/>
				</div>
				<div class="field full">
					<label for="html">Fichero</label>
					<input name="html" type="file"/>
				</div>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" value="Alta" name="commit" class="submit">
				<input type="button" value="Cancel" name="cancel" class="cancel">
			</fieldset>
		</sf:form>
	</div>
</div>