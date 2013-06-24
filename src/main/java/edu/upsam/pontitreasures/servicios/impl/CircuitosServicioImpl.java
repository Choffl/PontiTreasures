/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.persistencia.CircuitosRepository;
import edu.upsam.pontitreasures.servicios.CircuitosServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class CircuitosServicioImpl implements CircuitosServicio {
	
	@Autowired
	private CircuitosRepository circuitosRepository;

	@Override
	public Collection<Circuito> recuperarTodos() {
		return circuitosRepository.recuperarTodos();
	}

	@Override
	public Circuito recuperarPorId(Long circuitoId) {
		return circuitosRepository.recuperarPorId(circuitoId);
	}

}
