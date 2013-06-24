package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Usuario;

/**
 * @author ssabariego
 *
 */
public interface UsuariosServicio {

	/**
	 * @return
	 */
	Collection<Usuario> recuperarGestores();

	Usuario recuperarPorId(Long gestorId);

}
