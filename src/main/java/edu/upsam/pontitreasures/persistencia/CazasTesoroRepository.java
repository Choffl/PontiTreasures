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

	Collection<CazaTesoro> recuperarTodos();

}
