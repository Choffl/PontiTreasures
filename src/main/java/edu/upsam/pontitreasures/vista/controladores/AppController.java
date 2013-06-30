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

import edu.upsam.pontitreasures.servicios.JugadoresServicio;
import edu.upsam.pontitreasures.vista.controladores.excepciones.FalloServidorException;
import edu.upsam.pontitreasures.vista.json.CazaJSON;
import edu.upsam.pontitreasures.vista.json.JugadorJSON;

@Controller
@RequestMapping("/app")
public class AppController {

	@Autowired
	private JugadoresServicio jugadoresServicio;

	@RequestMapping(value="/cazas")
	@ResponseBody
	public Collection<CazaJSON> recuperarCazasActivas(){
		return new HashSet<CazaJSON>();
	}


	@RequestMapping(value="/app/jugador", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void altaAppJugador(@RequestBody JugadorJSON jugadorJSON){
		jugadoresServicio.alta(jugadorJSON.getUserName(), jugadorJSON.getEmail(), jugadorJSON.getPassword());
	}

	@ExceptionHandler(FalloServidorException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Datos incorrectos")
	public void handleAltaJugadortException(FalloServidorException exception, HttpServletResponse response) {
	}


}
