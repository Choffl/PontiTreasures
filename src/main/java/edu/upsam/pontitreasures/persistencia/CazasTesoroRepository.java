/**
 * 
 */
package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.CazaTesoro;

/**
 * @author ssabariego
 *
 */
public interface CazasTesoroRepository {

	/**
	 * @return
	 */
	Collection<CazaTesoro> recuperarTodos();

	/**
	 * @param propiedad
	 * @param valor
	 * @return
	 */
	Collection<CazaTesoro> recuperarPor(String propiedad, Object valor);
	
	/**
	 * @param id
	 * @return
	 */
	CazaTesoro recuperarPorId(Long id);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	void agregar(CazaTesoro cazaTesoro);

}
