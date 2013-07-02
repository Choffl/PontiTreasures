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
		<sf:form methodParam="POST" modelAttribute="etiquetaForm" action="etiquetas/etiqueta">
			<h2>Registra etiqueta</h2>
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
					<label for="longuitud">Longitud</label>
					<sf:input path="longuitud" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="latitud">Latitud</label>
					<sf:input path="latitud" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field multi">
					<label for="idPaginaAnonimo">Pagina checkin anonimo</label>
					<sf:select path="idPaginaAnonimo">
						<sf:option value="Escoge..." />
						<sf:options items="${paginas}" itemLabel="nombre" itemValue="id"/>
					</sf:select>
				</div>
				<div class="field multi">
					<label for="idPaginaIdentificado">Pagina checkin registrado</label>
					<sf:select path="idPaginaIdentificado">
						<sf:option value="Escoge..." />
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