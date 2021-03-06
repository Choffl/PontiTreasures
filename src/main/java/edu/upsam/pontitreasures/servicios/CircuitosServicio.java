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
	
	/**
	 * @param circuitoId
	 * @return
	 */
	Circuito recuperarPorIdConEtiquetas(Long circuitoId);

	/**
	 * @param nombre
	 * @param descripcion
	 * @param etiquetas
	 */
	void alta(String nombre, String descripcion, Collection<Long> etiquetas);

	/**
	 * @param circuito
	 */
	void actualizar(Circuito circuito);

	/**
	 * @param circuitoId
	 * @return
	 */
	boolean eliminar(Long circuitoId);


}
