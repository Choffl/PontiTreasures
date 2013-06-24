/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Etiqueta;

/**
 * @author ssabariego
 *
 */
public interface EtiquetasServicio {

	/**
	 * @return
	 */
	Collection<Etiqueta> recuperarTodas();

	/**
	 * @param etiquetaId
	 * @return
	 */
	Etiqueta recuperarPorId(Long etiquetaId);
	
	/**
	 * @param etiquetaId
	 * @return
	 */
	byte[] recuperarImagenQR(Long etiquetaId);

}
