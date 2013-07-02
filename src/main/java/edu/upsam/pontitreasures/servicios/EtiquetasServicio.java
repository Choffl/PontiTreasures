/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.PaginaJuego;

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
	Boolean eliminar(Long etiquetaId);
	
	/**
	 * @param etiquetaId
	 * @return
	 */
	byte[] recuperarImagenQR(Long etiquetaId);

	/**
	 * @param codigo
	 * @param descripcion
	 * @param latitud
	 * @param longuitud
	 * @param paginaAnonimo
	 * @param paginaIdentificado
	 */
	void alta(String codigo, String descripcion, String latitud, String longuitud, PaginaJuego paginaAnonimo, PaginaJuego paginaIdentificado);

	/**
	 * @param codigoQR
	 * @return
	 */
	Etiqueta recuperarPorCodigo(String codigoQR);

}
