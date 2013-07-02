<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<script>
	$(document).ready(function() {
		$('input[type="button"]').bind('click', function(event) {
			$('.formDialog').css("display","none");
		});
		$('.accionAlta a').bind('click', function(event) {
			$('.formDialog').css("display","block");	
		});
	});
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
						<li class="eliminar">
							<s:url value="/paginas/{id}" var="urlEliminarPagina">
								<s:param name="id" value="${pagina.id}"/>
							</s:url>
							<a href="${urlEliminarPagina}"><i class="icon-trash icon-2x"></i></a>
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
<div id="formularioAlta" class="formDialog">
	<div class="dialog">
		<sf:form methodParam="POST" modelAttribute="paginaForm" action="paginas/pagina" enctype="multipart/form-data">
			<h2>Registra pagina</h2>
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
					<label for="html">Descripcion</label>
					<input type="file" name="html "/>
				</div>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" value="Alta" name="commit" class="submit">
				<input type="button" value="Cancel" name="cancel" class="cancel">
			</fieldset>
		</sf:form>
	</div>
</div>