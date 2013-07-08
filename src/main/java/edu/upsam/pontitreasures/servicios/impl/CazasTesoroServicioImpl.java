/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.EstadoCaza;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.CazasTesoroRepository;
import edu.upsam.pontitreasures.persistencia.CheckinsRepository;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class CazasTesoroServicioImpl implements CazasTerosoroServicio {
	
	@Autowired
	private CazasTesoroRepository cazasTesoroRepository;
	
	@Autowired
	private CheckinsRepository checkinsRepository;

	/* (non-Javadoc)
	 * @see edu.upsam.pontitreasures.servicios.CazasTerosoroServicio#recuperarTodos()
	 */
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Collection<CazaTesoro> recuperarTodos() {
		return cazasTesoroRepository.recuperarTodos();
	}



	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Collection<CazaTesoro> recuperarActivas() {
		return cazasTesoroRepository.recuperarPor("estadoCaza", EstadoCaza.ACTIVA);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public CazaTesoro recuperarPorId(Long id) {
		return cazasTesoroRepository.recuperarPorId(id);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void alta(String nombre, Circuito circuito, Usuario gestor, PaginaJuego paginaPremioAnonimo, PaginaJuego paginaPremioIdentificado,
			Integer condicionPremio, Integer condicionMencion) {
		CazaTesoro cazaTesoro = new CazaTesoro();
		cazaTesoro.setCircuito(circuito);
		cazaTesoro.setEstadoCaza(EstadoCaza.NUEVA);
		cazaTesoro.setGestor(gestor);
		cazaTesoro.setNombre(nombre);
		cazaTesoro.setPaginaPremioAnonimo(paginaPremioAnonimo);
		cazaTesoro.setPaginaPremioIdentificado(paginaPremioIdentificado);
		cazaTesoro.setNumeroCheckinMencion(condicionMencion);
		cazaTesoro.setNumeroCheckinPremio(condicionPremio);

		 cazasTesoroRepository.agregar(cazaTesoro);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void actualizar(CazaTesoro cazaTesoro) {
		cazasTesoroRepository.actualizar(cazaTesoro);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public boolean eliminar(Long cazaId) {
		boolean eliminado = Boolean.FALSE;
		CazaTesoro cazaTesoro = cazasTesoroRepository.recuperarPorId(cazaId);
		if(esEliminable(cazaTesoro)){
			cazasTesoroRepository.eliminar(cazaTesoro);
			eliminado = Boolean.TRUE;
		}
		return eliminado;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void activar(Long cazaId) {
		CazaTesoro cazaTesoro = cazasTesoroRepository.recuperarPorId(cazaId);
		cazaTesoro.setEstadoCaza(EstadoCaza.ACTIVA);
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void cerrar(Long cazaId) {
		CazaTesoro cazaTesoro = cazasTesoroRepository.recuperarPorId(cazaId);
		cazaTesoro.setEstadoCaza(EstadoCaza.CERRADO);
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Integer calcularCheckinsAnonimos(CazaTesoro cazaTesoro) {
		return checkinsRepository.recuperarCheckinsAnonimos(cazaTesoro).size(); 
	}



	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Integer calcularCheckinsIdentificados(CazaTesoro cazaTesoro) {
		return checkinsRepository.recuperarCheckinsIdentificados(cazaTesoro).size();
	}



	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Integer calcularPremiosAnonimos(CazaTesoro cazaTesoro) {
		return checkinsRepository.recuperarPremiosAnonimos(cazaTesoro).size();
	}



	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Integer calcularPremiosIdentificados(CazaTesoro cazaTesoro) {
		return checkinsRepository.recuperarPremiosIdentificados(cazaTesoro).size();
	}
	
	private boolean esEliminable(CazaTesoro cazaTesoro) {
		if(cazaTesoro.getEstadoCaza().equals(EstadoCaza.CERRADO) && !cazasTesoroRepository.tieneJugadores(cazaTesoro) && !cazasTesoroRepository.tieneCheckins(cazaTesoro)){
			return true;
		}
		return false;
	}

}
