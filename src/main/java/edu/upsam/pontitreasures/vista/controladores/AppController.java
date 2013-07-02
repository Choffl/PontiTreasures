package edu.upsam.pontitreasures.vista.controladores;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.servicios.AutenticacionServicio;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;
import edu.upsam.pontitreasures.servicios.CheckinsServicio;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;
import edu.upsam.pontitreasures.servicios.PaginasServicio;
import edu.upsam.pontitreasures.vista.controladores.excepciones.FalloServidorException;
import edu.upsam.pontitreasures.vista.json.CazaJSON;
import edu.upsam.pontitreasures.vista.json.CheckinJSON;
import edu.upsam.pontitreasures.vista.json.JugadorJSON;
import edu.upsam.pontitreasures.vista.json.LoginJSON;
import edu.upsam.pontitreasures.vista.json.RegistriCazaJSON;

@Controller
@RequestMapping("/app")
public class AppController {

	/**
	 * 
	 */
	@Autowired
	private JugadoresServicio jugadoresServicio;

	@Autowired
	private AutenticacionServicio autenticacionServicio;

	@Autowired
	private CazasTerosoroServicio cazasTerosoroServicio;
	
	@Autowired
	private PaginasServicio paginasServicio;
	
	@Autowired
	private CheckinsServicio checkinsServicio;


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
				if(jugador.getIdentificador() == null){
					jugadoresServicio.registraIdentificador(jugador.getId(), loginJSON.getIdentificador());
				}
				jugadorJSON.setUserName(jugador.getUsername());
				jugadorJSON.setIdentificador(loginJSON.getIdentificador());
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

	@RequestMapping(value="/cazas")
	@ResponseBody
	public Collection<CazaJSON> recuperarCazasActivas(){
		Collection<CazaJSON> cazasJSON = new HashSet<CazaJSON>();
		Collection<CazaTesoro> cazasTesoro = cazasTerosoroServicio.recuperarActivas();
		for (CazaTesoro cazaTesoro : cazasTesoro) {
			cazasJSON.add(new CazaJSON(cazaTesoro.getId(), cazaTesoro.getNombre()));
		}
		return cazasJSON;
	}
	
	@RequestMapping(value="/jugador/caza", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void apuntarJugadorACaza(@RequestBody RegistriCazaJSON registroCaza){
		Jugador jugador = jugadoresServicio.recuperarPorIdentificador(registroCaza.getJugadorIdentificador());
		if(jugador == null){
			jugador = jugadoresServicio.registraJugadorAnonimo(registroCaza.getJugadorIdentificador());
		}
		jugadoresServicio.registrarParticipacionCaza(jugador.getId(), Long.valueOf(registroCaza.getCazaId()));
	}
	
	@RequestMapping(value="/checkin", method=RequestMethod.POST, produces=MediaType.TEXT_HTML_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody byte[] registrarCheckin(@RequestBody CheckinJSON checkinJSON){
		PaginaJuego paginaJuego = checkinsServicio.registroCheckin(checkinJSON.getJugadorIdentidicador(), checkinJSON.getCodigoQR(), Long.valueOf(checkinJSON.getCazaId()), checkinJSON.getLatitud(), checkinJSON.getLongitud());
		return paginaJuego.getPaginaHtml();
	}

}
