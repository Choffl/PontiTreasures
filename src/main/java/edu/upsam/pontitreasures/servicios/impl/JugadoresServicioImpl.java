package edu.upsam.pontitreasures.servicios.impl;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.persistencia.CheckinsRepository;
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
	
	@Autowired
	private CheckinsRepository checkinsRepository;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public Jugador alta(String username, String email, String password) {
		Jugador jugador = new Jugador(username, email, password);
		jugador.setIdentificador(generarHash(email));
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
		Jugador jugador = new Jugador("Anonimo_"+jugadorIdentificador, null, null);
		jugador.setIdentificador(generarHash(jugadorIdentificador));
		jugadoresRepository.agregar(jugador);
		return jugador;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Collection<Jugador> recuperarTodos() {
		return jugadoresRepository.recuperarTodos();
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Jugador recuperarPorId(Long jugadorId) {
		return (Jugador)jugadoresRepository.recuperarPorId(jugadorId);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Integer calcularCheckins(Jugador jugador) {
		return checkinsRepository.recuperarCheckins(jugador).size();
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Integer calcularPremios(Jugador jugador) {
		return checkinsRepository.recuperarPremios(jugador).size();
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean tienePremio(Jugador jugador, CazaTesoro cazaTesoro) {
		return !checkinsRepository.recuperaPremios(jugador, cazaTesoro).isEmpty();
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Jugador recuperarPorImei(String imei) {
		return jugadoresRepository.recuperarPorIdentificador(generarHash(imei));
	}

	private String generarHash(String imei) {
		MessageDigest m;
		String hash ="";
		try {
			m = MessageDigest.getInstance("MD5");
	        m.update(imei.getBytes(),0,imei.length());
	        hash = new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

}
