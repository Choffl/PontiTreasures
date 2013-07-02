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

	/**
	 * @param etiqueta
	 */
	void agregar(Etiqueta etiqueta);

	/**
	 * @param etiquetaId
	 */
	void eliminar(Long etiquetaId);

	/**
	 * @param etiquetaId
	 * @return
	 */
	boolean consultaAsociadoCircuito(Long etiquetaId);

	/**
	 * @param string
	 * @param codigoQR
	 */
	Etiqueta recuperarUnicoPor(String campo, String valor);


}
