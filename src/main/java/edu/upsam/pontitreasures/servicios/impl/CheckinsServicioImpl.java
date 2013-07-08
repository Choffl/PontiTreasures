package edu.upsam.pontitreasures.servicios.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Checkin;
import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.persistencia.CheckinsRepository;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;
import edu.upsam.pontitreasures.servicios.CheckinsServicio;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;
import edu.upsam.pontitreasures.servicios.PaginasServicio;

@Service
public class CheckinsServicioImpl implements CheckinsServicio {
	
	@Autowired
	private CheckinsRepository checkinsRepository;
	
	@Autowired
	private JugadoresServicio jugadoresServicio;
	
	@Autowired
	private EtiquetasServicio etiquetasServicio;
	
	@Autowired
	private CazasTerosoroServicio cazasTerosoroServicio;
	
	@Autowired
	private PaginasServicio paginasServicio;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public PaginaJuego registroCheckin(String identificadorJugador, String codigoQR, Long cazaId, String latitud, String longitud) {
		PaginaJuego paginaResultado = null;
		Jugador jugador = jugadoresServicio.recuperarPorIdentificador(identificadorJugador);
		BigDecimal latitudValue = new BigDecimal(latitud);
		BigDecimal longitudValue = new BigDecimal(longitud);
		Etiqueta etiqueta = etiquetasServicio.recuperarPorCodigo(codigoQR);
		
		if(etiqueta == null){
			return paginasServicio.recuperaPaginaCheckinIncorrecto();
		}

		if(coordenadasCorrectas(etiqueta, latitudValue, longitudValue)){
			paginaResultado = checkinCorrecto(cazaId, jugador, etiqueta);
		}else{
			paginaResultado = checkinIncorrecto(cazaId, jugador, etiqueta);
		}
		

		return paginaResultado;
		
	}

	private PaginaJuego checkinIncorrecto(Long cazaId, Jugador jugador, Etiqueta etiqueta) {
		Boolean anonimo;
		
		if(jugadorAnonimo(jugador)){
			anonimo = Boolean.TRUE;
		}else{
			anonimo = Boolean.FALSE;
		}
		
		Checkin checkin = crearCheckin(cazaId, jugador, anonimo,true, etiqueta);
		
		checkinsRepository.agregar(checkin);
		return paginasServicio.recuperaPaginaCheckinIncorrecto();
	}

	private PaginaJuego checkinCorrecto(Long cazaId, Jugador jugador, Etiqueta etiqueta) {
		PaginaJuego paginaResultado;
		Boolean anonimo;
		
		if(jugadorAnonimo(jugador)){
			paginaResultado = etiqueta.getPaginaCheckinAnonimo();
			anonimo = Boolean.TRUE;
		}else{
			paginaResultado = etiqueta.getPaginaCheckinIdentificado();
			anonimo = Boolean.FALSE;
		}
		
		Checkin checkin = crearCheckin(cazaId, jugador, anonimo,false, etiqueta);
		
		checkinsRepository.agregar(checkin);
		return paginaResultado;
	}

	private boolean coordenadasCorrectas(Etiqueta etiqueta,	BigDecimal latitudValue, BigDecimal longitudValue) {
		return (etiqueta.getLatitud().stripTrailingZeros().equals(latitudValue) && etiqueta.getLongitud().stripTrailingZeros().equals(longitudValue));
	}

	private Checkin crearCheckin(Long cazaId, Jugador jugador, Boolean anonimo, Boolean incorrecto, Etiqueta etiqueta) {
		CazaTesoro cazaTesoro = cazasTerosoroServicio.recuperarPorId(cazaId);
		Checkin checkin = new Checkin();
		checkin.setCazaTesoro(cazaTesoro);
		checkin.setEtiqueta(etiqueta);
		checkin.setJugador(jugador);
		checkin.setIncorrecto(incorrecto);
		checkin.setFecha(new Date(System.currentTimeMillis()));
		return checkin;
	}

	private boolean jugadorAnonimo(Jugador jugador) {
		return jugador.getIdentificador().equals(jugador.getUsername());
	}

}
