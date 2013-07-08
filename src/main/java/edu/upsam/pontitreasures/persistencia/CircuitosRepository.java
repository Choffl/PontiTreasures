package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Circuito;

/**
 * @author ssabariego
 *
 */
public interface CircuitosRepository {

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
	 * @param circuito
	 */
	void agrega(Circuito circuito);

	/**
	 * @param circuito
	 */
	void actualizar(Circuito circuito);

	/**
	 * @param circuitoId
	 * @return
	 */
	Circuito recuperarPorIdConEtiquetas(Long circuitoId);

	/**
	 * @param circuito
	 * @return
	 */
	boolean esUsadoCaza(Circuito circuito);

	/**
	 * @param circuito
	 */
	void eliminar(Circuito circuito);

}
