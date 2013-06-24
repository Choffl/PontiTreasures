<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<section>
	<div id="alta" class="dialog">
		<sf:form methodParam="POST" modelAttribute="jugadorForm" action="alta">
			<h2>Registro jugador</h2>
			<sf:errors path="*" cssClass="errorMessages"/>
			<fieldset>
				<div class="field">
					<label for="username">Usuario</label>
					<sf:input path="username" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field">
					<label for="email">Email</label>
					<sf:input path="email" spellcheck="false" cssClass="text"/>
				</div>
				<div class="field">
					<label for="password">Password</label>
					<sf:password path="password" cssClass="text"/>
				</div>
				<div class="field">
					<label for="passwordRepetido">Repita password</label>
					<sf:password path="passwordRepetido" cssClass="text"/>
				</div>
			</fieldset>
			<fieldset class="submit">
				<input type="submit" value="Alta" name="commit" class="submit">
			</fieldset>
		</sf:form>
	</div>


</section>