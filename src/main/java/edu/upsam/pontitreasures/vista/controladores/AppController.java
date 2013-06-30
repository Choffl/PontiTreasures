package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.servicios.AutenticacionServicio;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;
import edu.upsam.pontitreasures.vista.controladores.excepciones.FalloServidorException;
import edu.upsam.pontitreasures.vista.json.CazaJSON;
import edu.upsam.pontitreasures.vista.json.JugadorJSON;
import edu.upsam.pontitreasures.vista.json.LoginJSON;

@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private JugadoresServicio jugadoresServicio;
	
	@Autowired
	private AutenticacionServicio autenticacionServicio;

	@RequestMapping(value="/cazas")
	@ResponseBody
	public Collection<CazaJSON> recuperarCazasActivas(){
		return new HashSet<CazaJSON>();
	}


	@RequestMapping(value="/jugador", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void altaAppJugador(@RequestBody JugadorJSON jugadorJSON) throws FalloServidorException{
		try{
			jugadoresServicio.alta(jugadorJSON.getUserName(), jugadorJSON.getEmail(), jugadorJSON.getPassword());
		}catch (Exception e) {
			throw new FalloServidorException();
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody JugadorJSON autenticaJugador(@RequestBody LoginJSON loginJSON) throws FalloServidorException{
		JugadorJSON jugadorJSON = new JugadorJSON();
		try{
			Jugador jugador = (Jugador)autenticacionServicio.autenticar(loginJSON.getEmail(), loginJSON.getPassword());
			if(jugador != null){
				jugadorJSON.setUserName(jugador.getUsername());
				jugadorJSON.setIdentificador(loginJSON.getIdentificador());
				if(jugador.getIdentificador().isEmpty()){
					jugadoresServicio.registraIdentificador(jugador.getId(), loginJSON.getIdentificador());
				}
			}
		}catch (Exception e) {
			throw new FalloServidorException();
		}
		return jugadorJSON;
	}

	@ExceptionHandler(FalloServidorException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Datos incorrectos")
	public void handleAltaJugadortException(FalloServidorException exception, HttpServletResponse response) {
	}


}
