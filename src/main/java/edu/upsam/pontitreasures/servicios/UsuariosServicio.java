package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.TipoUsuario;
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

	/**
	 * @param gestorId
	 * @return
	 */
	Usuario recuperarPorId(Long gestorId);

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param gestor
	 */
	void alta(String username, String email, String password, TipoUsuario gestor);

	/**
	 * @param usuario
	 */
	void actualizar(Usuario usuario);

	/**
	 * @param gestorId
	 * @return
	 */
	boolean eliminar(Long gestorId);

}
