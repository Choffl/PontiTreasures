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

	/**
	 * @param id
	 * @param identificador
	 */
	void registraIdentificador(Long id, String identificador);
	
	/**
	 * @param id
	 * @param cazaTesoroId
	 */
	void registrarParticipacionCaza(Long id, Long cazaTesoroId);

	/**
	 * @param string
	 * @param jugadorIdentificador
	 * @return
	 */
	Jugador recuperarPorIdentificador(String jugadorIdentificador);
	
	/**
	 * 
	 */
	Jugador registraJugadorAnonimo(String jugadorIdentificador);

}
