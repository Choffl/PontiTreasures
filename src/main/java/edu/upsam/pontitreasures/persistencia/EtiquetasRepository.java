package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Etiqueta;

/**
 * @author ssabariego
 *
 */
public interface EtiquetasRepository {

	/**
	 * @return
	 */
	Collection<Etiqueta> recuperarTodas();
	
	/**
	 * @param id
	 * @return
	 */
	Etiqueta recuperarPorId(Long id);

}
