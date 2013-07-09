<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>


<div id="jugadores">
	<ul class="listado">
		<c:forEach items="${jugadores}" var="jugador">
			<li class="elemento">
				<div class="item">
					<hgroup class="nombreElemento">
						<h2>
							<i class="icon-mobile-phone"></i>
							<s:url value="/jugadores/{id}" var="urlVerJugador">
								<s:param name="id" value="${jugador.id}"/>
							</s:url>
							<a href="${urlVerJugador}">${jugador.username}</a>
						</h2>
					</hgroup>
					<ul class="listado-acciones">
						<li class="ver">
							<a href="${urlVerJugador}"><i class="icon-eye-open icon-2x"></i></a>
						</li>
						<li class="mostrarGrafica">
							<s:url value="/jugadores/{id}" var="urlMostrarGraficas">
								<s:param name="id" value="${jugador.id}"/>
								<s:param name="action" value="graphs"/>
							</s:url>
							<a href="${urlMostrarGraficas}"><i class="icon-bar-chart icon-2x"></i></a>
						</li>
					</ul>		
				</div>			
			</li>
		</c:forEach>
	</ul>
</div>

