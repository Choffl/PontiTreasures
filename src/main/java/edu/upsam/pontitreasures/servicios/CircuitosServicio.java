package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Circuito;

/**
 * @author ssabariego
 *
 */
public interface CircuitosServicio {

	/**
	 * @return
	 */
	Collection<Circuito> recuperarTodos();

	/**
	 * @param circuitoId
	 * @return
	 */
	Circuito recuperarPorId(Long circuitoId);


}
