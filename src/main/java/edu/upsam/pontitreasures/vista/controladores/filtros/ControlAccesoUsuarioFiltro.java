package edu.upsam.pontitreasures.vista.controladores.filtros;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Especifica un filtro que se ejecutara por cada peticion request
 * que cumpla el patron que se especifica en su configuracion.
 * Comprobara que exista un usuario autenticado en la aplicacion,
 * de no ser asi redirigira a la pantalla de inicio
 */
public class ControlAccesoUsuarioFiltro extends HandlerInterceptorAdapter {
	
	/**
	 * Log4J
	 */
	private static final Logger logger = Logger.getLogger(ControlAccesoUsuarioFiltro.class);
	
	/**
	 * El nombre de la variable de sesion que guarda el usuario.
	 */
	private final static String USUARIO_SESSION = "usuarioEnSession";
	
	/**
	 * La URL de la pagina de Login
	 */
	private String loginURL;

	/**
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(isUsuarioLogueado(request) && notPeticionLogin(request)){
			return true;
		}
		
		logger.warn("El usuario no esta logueado");
		response.sendRedirect(loginURL);
		return false;
	}

	/**
	 * @return the loginURL
	 */
	public String getLoginURL() {
		return loginURL;
	}

	/**
	 * @param loginURL the loginURL to set
	 */
	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	/*
	 * Devuelve si el usuarios esta logueado o no
	 */
	private boolean isUsuarioLogueado(HttpServletRequest request) {
		return request.getSession() != null && request.getSession().getAttribute(USUARIO_SESSION) != null;
	}
	
	/*
	 * Devuelve si la request en curso es una peticion de login o no.
	 */
	private boolean notPeticionLogin(HttpServletRequest request) {
		return !request.getRequestURI().contains(loginURL);
	}

}
