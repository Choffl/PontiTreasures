<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<header>
	<h1>PontiTreasures</h1>
</header>

<section>
	<div id="login" class="dialog">
		<sf:form methodParam="POST" modelAttribute="loginForm" action="login">
			<h2>Login</h2>
			<sf:errors path="*" cssClass="errorMessages"/>
			<fieldset>
				<div class="field">
					<label for="email">Email</label>
					<sf:input path="email" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field">
					<label for="password">Password</label>
					<sf:password path="password" cssClass="text"/>
				</div>
			</fieldset>
			<fieldset class="submit">
				<p><a href="">Resetear</a></p>
				<input type="submit" value="Log In" name="commit" class="submit">
			</fieldset>
		</sf:form>
	</div>
	<div class="dialog">
		<s:url value="/aplicacion/jugador/alta" var="nuevoJugadorURL"/>
		<p>¿Quieres encontrar el tesoro? <span id="registrar"><a href="${nuevoJugadorURL}">Registrate</a></span></p>
	</div>

</section>