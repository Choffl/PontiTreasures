/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import edu.upsam.pontitreasures.dominio.Jugador;

/**
 * @author ssabariego
 *
 */
public interface JugadoresServicio {

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @return
	 */
	Jugador alta(String username, String email, String password);
	
	/**
	 * @param email
	 * @return
	 */
	Boolean isEmailRegistrado(String email);
	
	/**
	 * @param username
	 * @return
	 */
	Boolean isNombreUsuarioRegistrado(String username);

}
