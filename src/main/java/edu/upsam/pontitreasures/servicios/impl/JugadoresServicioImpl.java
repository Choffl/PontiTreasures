package edu.upsam.pontitreasures.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.persistencia.JugadoresRepository;
import edu.upsam.pontitreasures.servicios.JugadoresServicio;

@Service
public class JugadoresServicioImpl implements JugadoresServicio{
	
	@Autowired
	@Qualifier("jugadoresRepository")
	private JugadoresRepository jugadoresRepository;

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

}
