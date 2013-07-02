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
	void agregar(PaginaJuego paginaJuego);

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	PaginaJuego recuperaUnicoPor(String propiedad, String valor);

}
