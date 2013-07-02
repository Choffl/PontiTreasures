package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.persistencia.JugadoresRepository;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;

@Service
public class JugadoresServicioImpl implements JugadoresServicio{
	
	@Autowired
	@Qualifier("jugadoresRepository")
	private JugadoresRepository jugadoresRepository;
	
	@Autowired
	private CazasTerosoroServicio cazasTerosoroServicio;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public Jugador alta(String username, String email, String password) {
		Jugador jugador = new Jugador(username, email, password);
		jugadoresRepository.agregar(jugador);
		return jugador;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Boolean isEmailRegistrado(String email) {
		return false;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Boolean isNombreUsuarioRegistrado(String username) {
		return false;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void registraIdentificador(Long id, String identificador) {
		Jugador jugador = (Jugador)jugadoresRepository.recuperarPorId(id);
		jugador.setIdentificador(identificador);
		jugadoresRepository.actualizar(jugador);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void registrarParticipacionCaza(Long id, Long cazaTesoroId) {
		Jugador jugador = (Jugador)jugadoresRepository.recuperarPorId(id);
		CazaTesoro cazaTesoro = cazasTerosoroServicio.recuperarPorId(cazaTesoroId);
		jugador.setCazaTesoroActiva(cazaTesoro);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Jugador recuperarPorIdentificador(String jugadorIdentificador) {
		Collection<Jugador> jugadores = jugadoresRepository.recuperarPor("identificador", jugadorIdentificador);
		if(jugadores.isEmpty()){
			return null;
		}
		return jugadores.toArray(new Jugador[jugadores.size()])[0];
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Jugador registraJugadorAnonimo(String jugadorIdentificador) {
		Jugador jugador = new Jugador(jugadorIdentificador, null, jugadorIdentificador);
		jugador.setIdentificador(jugadorIdentificador);
		jugadoresRepository.agregar(jugador);
		return jugador;
	}

}
