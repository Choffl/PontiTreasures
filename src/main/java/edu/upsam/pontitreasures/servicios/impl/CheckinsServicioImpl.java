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
import edu.upsam.pontitreasures.dominio.TipoPagina;
import edu.upsam.pontitreasures.persistencia.CheckinsRepository;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;
import edu.upsam.pontitreasures.servicios.CheckinsServicio;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;
import edu.upsam.pontitreasures.servicios.PaginasServicio;

@Service
public class CheckinsServicioImpl implements CheckinsServicio {
	
	private static final Integer N = 10;
	
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
		Etiqueta etiqueta = etiquetasServicio.recuperarPorCodigo(codigoQR);
		Jugador jugador = jugadoresServicio.recuperarPorIdentificador(identificadorJugador);
		CazaTesoro cazaTesoro = cazasTerosoroServicio.recuperarPorId(cazaId);
		
		if(etiquetaNoExiste(etiqueta)){
			return paginasServicio.recuperarPaginaPorTipo(TipoPagina.CODIGO_ERRONEO);
		}
		
		if(cazaNoRegistrada(cazaTesoro, jugador)){
			return paginasServicio.recuperarPaginaPorTipo(TipoPagina.CAZA_ERRONEA);
		}
		
		if(localizacionErronea(latitud,longitud, etiqueta)){
			return paginasServicio.recuperarPaginaPorTipo(TipoPagina.CAZA_ERRONEA);
		}
		
		Date fechaActual = new Date(System.currentTimeMillis());
		if(checkingRepetido(etiqueta, jugador, cazaTesoro, fechaActual)){
			return paginasServicio.recuperarPaginaPorTipo(TipoPagina.CHECKIN_REPETIDO);
		}
		
		Checkin checkin = null;
		if(!jugadoresServicio.tienePremio(jugador, cazaTesoro)){
			Integer numeroCheckinsJugador = checkinsRepository.recuperarCheckinsCaza(jugador, cazaTesoro).size();
			Integer numeroCheckinsDistintosJugador = checkinsRepository.recuperarCheckinsDistintosCaza(jugador, cazaTesoro).size();
			
			if(cumpleCondicionesPremio(numeroCheckinsJugador, numeroCheckinsDistintosJugador, cazaTesoro)){
				checkin = crearCheckin(cazaTesoro, jugador, etiqueta, fechaActual, latitud, longitud, Boolean.TRUE, Boolean.FALSE);
				return seleccionaPaginaPremioJugador(jugador, cazaTesoro);
			}
		}
		
		if(cumpleCondicionesMencion(jugador)){
			checkin = crearCheckin(cazaTesoro, jugador, etiqueta, fechaActual, latitud, longitud, Boolean.FALSE, Boolean.TRUE);
			return paginasServicio.recuperarPaginaPorTipo(TipoPagina.MENCION);
		}
		
		checkin = crearCheckin(cazaTesoro, jugador, etiqueta, fechaActual, latitud, longitud, Boolean.FALSE, Boolean.FALSE);

		return seleccionaPaginaCheckinJugador(jugador, etiqueta);
		
	}

	private PaginaJuego seleccionaPaginaCheckinJugador(Jugador jugador,	Etiqueta etiqueta) {
		PaginaJuego paginaJuego = null;
		if(jugadorAnonimo(jugador)){
			paginaJuego = etiqueta.getPaginaCheckinAnonimo();
		}else{
			paginaJuego = etiqueta.getPaginaCheckinIdentificado();
		}
		return paginaJuego;
	}

	private PaginaJuego seleccionaPaginaPremioJugador(Jugador jugador, CazaTesoro cazaTesoro) {
		PaginaJuego paginaJuego = null;
		if(jugadorAnonimo(jugador)){
			paginaJuego = cazaTesoro.getPaginaPremioAnonimo();
		}else{
			paginaJuego = cazaTesoro.getPaginaPremioIdentificado();
		}
		return paginaJuego;
	}
	
	private boolean cumpleCondicionesMencion(Jugador jugador) {
		Integer numeroCheckins = checkinsRepository.recuperarCheckins(jugador).size();
		if(numeroCheckins % N == 0){
			return true;
		}
		return false;
	}

	private boolean cumpleCondicionesPremio(Integer numeroCheckinsJugador, Integer numeroCheckinsDistintosJugador, CazaTesoro cazaTesoro) {
		if((numeroCheckinsJugador + 1) > cazaTesoro.getNumeroCheckinPremio() && (numeroCheckinsDistintosJugador > cazaTesoro.getNumeroDistintoCheckinPremio())){
			return true;
		}
		return false;
	}

	private boolean checkingRepetido(Etiqueta etiqueta, Jugador jugador, CazaTesoro cazaTesoro, Date fechaActual) {
		return !checkinsRepository.buscarCheckinPor(etiqueta, jugador, cazaTesoro, fechaActual).isEmpty();
	}

	private boolean localizacionErronea(String latitud, String longitud, Etiqueta etiqueta) {
		BigDecimal latitudValue = new BigDecimal(latitud);
		BigDecimal longitudValue = new BigDecimal(longitud);
		return !(etiqueta.getLatitud().stripTrailingZeros().equals(latitudValue) && etiqueta.getLongitud().stripTrailingZeros().equals(longitudValue));
	}

	private boolean cazaNoRegistrada(CazaTesoro cazaTesoro, Jugador jugador) {
		return jugador.getCazaTesoroActiva().equals(cazaTesoro);
	}

	private boolean etiquetaNoExiste(Etiqueta etiqueta) {
		return etiqueta==null;
	}

	private Checkin crearCheckin(CazaTesoro cazaTesoro, Jugador jugador, Etiqueta etiqueta, Date fecha, String latitud, String longitud, Boolean premio, Boolean mencion) {
		Checkin checkin = new Checkin();
		checkin.setCazaTesoro(cazaTesoro);
		checkin.setEtiqueta(etiqueta);
		checkin.setJugador(jugador);
		checkin.setFecha(fecha);
		checkin.setLatitud(new BigDecimal(latitud));
		checkin.setLongitud(new BigDecimal(longitud));
		
		checkinsRepository.agregar(checkin);
		return checkin;
	}

	private boolean jugadorAnonimo(Jugador jugador) {
		return jugador.getEmail() == null;
	}

}
