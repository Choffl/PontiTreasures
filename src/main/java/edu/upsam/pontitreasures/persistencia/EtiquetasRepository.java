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
	 * @param etiqueta
	 */
	void eliminar(Etiqueta etiqueta);

	/**
	 * @param etiqueta
	 * @return
	 */
	boolean esUsadoCircuito(Etiqueta etiqueta);

	/**
	 * @param string
	 * @param codigoQR
	 */
	Etiqueta recuperarUnicoPor(String campo, String valor);

	/**
	 * @param etiqueta
	 */
	void actualizar(Etiqueta etiqueta);

	/**
	 * @param etiqueta
	 * @return
	 */
	boolean tieneCheckins(Etiqueta etiqueta);


}
