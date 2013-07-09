<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<script>
	$(document).ready(function() {
		$('input[type="button"]').bind('click', function(event) {
			$('.formDialog').css("display","none");
		});
		$('.accionAlta a').bind('click', function(event) {
			$('#gestorForm').find("input[type=text], input[type=hidden], input[type=password], textarea").val("");
        	$('#gestorForm').attr('action', 'gestores/gestor');
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
    	$('input[name="username"]').val(data.username);
    	$('input[name="email"]').val(data.email);
    	$('input[name="password"]').val(data.password);
    	$('input[name="id"]').val(data.id);
    	$('#gestorForm').attr('action', 'gestores/gestor?modify');
    	$('.mensajes').css("display","none");
    	$('.formDialog').css("display","block");
	}
	
</script>


<div id="gestores">
	<ul class="listado">
		<c:forEach items="${gestores}" var="gestor">
			<li class="elemento">
				<div class="item">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-user"></i>
							<s:url value="/gestores/{id}" var="urlVerGestor">
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
							<s:url value="/gestores/{id}" var="urlModificarGestor">
								<s:param name="id" value="${gestor.id}"/>
							</s:url>
							<a href="${urlModificarGestor}"><i class="icon-edit icon-2x"></i></a>
						</li>
						<li class="eliminar">
							<s:url value="/gestores/{id}" var="urlEliminarGestor">
								<s:param name="id" value="${gestor.id}"/>
								<s:param name="action" value="delete"/>
							</s:url>
							<a href="${urlEliminarGestor}"><i class="icon-trash icon-2x"></i></a>
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
		<sf:form id="gestorForm" methodParam="POST" modelAttribute="gestorForm" action="gestores/gestor">
			<h2>Alta gestor</h2>
			<sf:hidden path="id"/>
			<fieldset>
				<div class="field full">
					<label for="username">Login</label>
					<sf:input path="username" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field full">
					<label for="email">Email</label>
					<sf:input path="email" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field full">
					<label for="password">Password</label>
					<sf:password path="password" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field full">
					<label for="passwordRepetido">Password repetida</label>
					<sf:password path="passwordRepetido" spellcheck="false" cssClass="text"/>
				</div>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" value="Alta" name="commit" class="submit">
				<input type="button" value="Cancel" name="cancel" class="cancel">
			</fieldset>
		</sf:form>
	</div>
</div>