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

<div id="gestores">
	<ul class="listado">
		<c:forEach items="${gestores}" var="gestor">
			<li class="elemento">
				<div class="gestor">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-user"></i>
							<s:url value="/gestor/{id}" var="urlVerGestor">
								<s:param name="id" value="${gestor.id}"/>
							</s:url>
							<a href="${urlVerGestor}">${gestor.username}</a>
						</h2>
					</hgroup>
					<ul class="listado-acciones">
						<li class="ver">
							<a href="${urlVerGestor}"><i class="icon-eye-open icon-2x"></i></a>
						</li>
						<li class="modificar">
							<s:url value="/gestor/{id}" var="urlModificarGestor">
								<s:param name="id" value="${gestor.id}"/>
							</s:url>
							<a href="${urlModificarGestor}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<li class="eliminar">
							<s:url value="/gestor/{id}" var="urlEliminarGestor">
								<s:param name="id" value="${gestor.id}"/>
							</s:url>
							<a href="${urlEliminarGestor}"><i class="icon-trash icon-2x"></i></a>
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
		<sf:form methodParam="POST" modelAttribute="gestorForm" action="gestor">
			<h2>Alta gestor</h2>
			<fieldset>
				<div class="field full">
					<label for="username">Login</label>
					<sf:input path="username" spellcheck="false" cssClass="text"/>
				</div>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" value="Alta" name="commit" class="submit">
				<input type="button" value="Cancel" name="cancel" class="cancel">
			</fieldset>
		</sf:form>
	</div>
</div>