/**
 * 
 */
package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.PaginaJuego;

/**
 * @author ssabariego
 *
 */
public interface PaginasRepository {

	/**
	 * @return
	 */
	Collection<PaginaJuego> recuperarTodas();

	/**
	 * @param paginaId
	 * @return
	 */
	PaginaJuego recuperarPorId(Long paginaId);

	/**
	 * @param paginaJuego
	 */
	void persist(PaginaJuego paginaJuego);

}
