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

<div id="circuitos">
	<ul class="listado">
		<c:forEach items="${circuitos}" var="circuito">
			<li class="elemento">
				<div class="item">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-road"></i>
							<s:url value="/circuito/{id}" var="urlVerCircuito">
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
							<s:url value="/circuito/{id}" var="urlModificarCircuito">
								<s:param name="id" value="${circuito.id}"/>
							</s:url>
							<a href="${urlModificarCircuito}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<li class="eliminar">
							<s:url value="/circuito/{id}" var="urlEliminarCircuito">
								<s:param name="id" value="${circuito.id}"/>
							</s:url>
							<a href="${urlEliminarCircuito}"><i class="icon-trash icon-2x"></i></a>
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
		<sf:form methodParam="POST" modelAttribute="circuitoForm" action="circuitos/circuito">
			<h2>Registra cirtucito</h2>
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