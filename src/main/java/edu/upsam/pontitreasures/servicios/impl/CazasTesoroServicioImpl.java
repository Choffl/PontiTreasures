/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.CazasTesoroRepository;
import edu.upsam.pontitreasures.servicios.CazasTerosoroServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class CazasTesoroServicioImpl implements CazasTerosoroServicio {
	
	@Autowired
	private CazasTesoroRepository cazasTesoroRepository;

	/* (non-Javadoc)
	 * @see edu.upsam.pontitreasures.servicios.CazasTerosoroServicio#recuperarTodos()
	 */
	@Override
	public Collection<CazaTesoro> recuperarTodos() {
		return cazasTesoroRepository.recuperarTodos();
	}

	@Override
	public CazaTesoro alta(String nombre, Date fechaInicio, Date fechaFin, Circuito circuito, Usuario gestor) {
		// TODO Auto-generated method stub
		return null;
	}

}
