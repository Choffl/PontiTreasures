/**
 * 
 */
package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Usuario;

/**
 * @author ssabariego
 *
 */
public interface UsuariosRepository<T extends Usuario> {
	
	/**
	 * @param usuario
	 */
	void agregar(T usuario);
	
	/**
	 * @param usuario
	 */
	void actualizar(T usuario);
	
	/**
	 * @param id
	 * @return
	 */
	Usuario recuperarPorId(Long id);
	
	/**
	 * @param propiedad
	 * @param valor
	 * @return
	 */
	Usuario recuperarUnicoPor(String propiedad, Object valor);

	/**
	 * @param string
	 * @param valor
	 * @return
	 */
	Collection<T> recuperarPor(String string, Object valor);

	/**
	 * @param usuario
	 */
	void eliminar(T usuario);

	/**
	 * @param usuario
	 * @return
	 */
	boolean gestionaCazas(T usuario);
	
}
