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
		$("#fechaInicio").datepicker({
		      changeMonth: true,
		      changeYear: true
		});
		$("#fechaFin").datepicker({
		      changeMonth: true,
		      changeYear: true
		});
	});
</script>

<div id="cazas">
	<ul class="listado">
		<c:forEach items="${cazas}" var="caza">
			<li class="elemento">
				<div id="caza1" class="caza">
					<hgroup class="nombreElemento">
						<h2 class="element">
							<i class="icon-gamepad"></i>
							<s:url value="/caza/{id}" var="urlVerCaza">
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
							<s:url value="/cazas/{id}/graficas" var="urlMostrarGraficas">
								<s:param name="id" value="${caza.id}"/>
							</s:url>
							<a href="${urlMostrarGraficas}"><i class="icon-bar-chart icon-2x"></i></a>
						</li>
						<li class="modificar">
							<s:url value="/cazas/{id}" var="urlModificarCaza">
								<s:param name="id" value="${caza.id}"/>
							</s:url>
							<a href="${urlModificarCaza}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<li class="eliminar">
							<s:url value="/cazas/{id}" var="urlEliminarCaza">
								<s:param name="id" value="${caza.id}"/>
							</s:url>
							<a href="${urlEliminarCaza}"><i class="icon-trash icon-2x"></i></a>
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
		<sf:form methodParam="POST" modelAttribute="cazaForm" action="caza">
			<h2>Comienza el juego</h2>
			<fieldset>
				<div class="field full">
					<label for="nombre">Nombre</label>
					<sf:input path="nombre" spellcheck="false" cssClass="text"/>
				</div>

					<div class="field multi">
						<label for="fechaInicio">Inicio</label>
						<sf:input path="fechaInicio" spellcheck="false" cssClass="fecha"/>
					</div>
					<div class="field multi">
						<label for="fechaFin">Fin</label>
						<sf:input path="fechaFin" spellcheck="false" cssClass="fecha"/>
				</div>
				<div class="field full">
					<label for="gestor">Gestor</label>
					<sf:select path="gestorId">
						<sf:options items="${gestores}" itemLabel="email" itemValue="id" cssClass="text"/>
					</sf:select>
				</div>
				<div class="field full">
					<label for="circuitoId">Circuito</label>
					<sf:select path="circuitoId">
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