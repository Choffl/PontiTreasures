package edu.upsam.pontitreasures.vista.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.servicios.AutenticacionServicio;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;
import edu.upsam.pontitreasures.vista.formularios.JugadorForm;
import edu.upsam.pontitreasures.vista.formularios.LoginForm;


/**
 * @author ssabariego
 *
 */
@Controller
@RequestMapping(value="/aplicacion")
@SessionAttributes(types=Usuario.class, value="usuarioEnSession")
public class AplicacionController {
	
	/**
	 * EL servicio de autenticacion
	 */
	@Autowired
	private AutenticacionServicio autenticacionServicio;
	
	/**
	 * EL servicio de autenticacion
	 */
	@Autowired
	private JugadoresServicio jugadoresServicio;

	/**
	 * Muestra el login de usuario
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	@ModelAttribute
	public LoginForm muestraLogin(Model model){
		return new LoginForm();
	}
	
	/**
	 * Se comprueba los credenciales del usuario
	 * @param loginForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loguear(LoginForm loginForm, Model model, Errors errors){
		Usuario usuarioEnSession = autenticacionServicio.autenticar(loginForm.getEmail(), loginForm.getPassword());
		
		if(usuarioEnSession == null){
			mostrarErroresLogin(usuarioEnSession, errors);
			return "aplicacion/login";			
		}		
		
		model.addAttribute("usuarioEnSession", usuarioEnSession);
		String redireccion;
		switch (usuarioEnSession.getTipo()) {
		case JUGADOR:{
			redireccion = "redirect:/app";
			break;
		}		

		default:
			redireccion = "redirect:/cazas";
		}
		return redireccion;
	}


	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/jugador/alta", method=RequestMethod.GET)
	@ModelAttribute
	public JugadorForm solicitudNuevoJugador(Model model){
		return new JugadorForm();
	}
	
	/**
	 * 
	 * @param usuarioForm
	 * @param model
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/jugador/alta", method=RequestMethod.POST)
	public String altaUsuario(@Valid JugadorForm jugadorForm, Model model, Errors errors){
		if(!isDatosJugadorValidos(jugadorForm, errors)){
			return "aplicacion/jugador/alta";
		}
		
		Usuario usuarioEnSession = jugadoresServicio.alta(jugadorForm.getUsername(), jugadorForm.getEmail(), jugadorForm.getPassword());
		model.addAttribute("usuarioEnSession", usuarioEnSession);
		return "redirect:/aplicacion/jugador/app";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/salir", method=RequestMethod.GET)
	public String logout(SessionStatus status, ModelMap model, HttpServletRequest request){
	    model.clear();
	    status.setComplete();
		return "redirect:/aplicacion/login";
	}

	/*
	 * 
	 */
	private Boolean isDatosJugadorValidos(JugadorForm jugadorForm, Errors errors) {
		Boolean validos = Boolean.TRUE;
		if(emailYaRegistrado(jugadorForm.getEmail())){
			errors.reject("jugadorForm","El mail ya esta dado de alta");
			validos= Boolean.FALSE;
		}
		
		if(nombreUsuarioYaRegistrado(jugadorForm.getUsername())){
			errors.reject("jugadorForm","El nombre de usuario ya esta dado de alta");
			validos= Boolean.FALSE;
		}
		
		return validos;
	}
	
	/*
	 * 
	 */
	private Boolean nombreUsuarioYaRegistrado(String username) {
		return jugadoresServicio.isNombreUsuarioRegistrado(username);
	}

	/*
	 * 
	 */
	private boolean emailYaRegistrado(String email) {
		return jugadoresServicio.isEmailRegistrado(email);
	}


	/*
	 * Muestra el error apropiado segun el estado del usuario invalido
	 */
	private void mostrarErroresLogin(Usuario usuarioEnSession, Errors errors) {
		errors.reject("loginForm","El usuario y/o la contraseña no son validas");
	}
	

	/**
	 * @return el autenticacionServicio
	 */
	public AutenticacionServicio getAutenticacionServicio() {
		return autenticacionServicio;
	}

	/**
	 * @param autenticacionServicio the autenticacionServicio to set
	 */
	public void setAutenticacionServicio(AutenticacionServicio autenticacionServicio) {
		this.autenticacionServicio = autenticacionServicio;
	}

}
