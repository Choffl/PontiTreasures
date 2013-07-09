/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
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

	/**
	 * @return
	 */
	Collection<Jugador> recuperarTodos();

	/**
	 * @param gestorId
	 * @return
	 */
	Jugador recuperarPorId(Long gestorId);

	/**
	 * @param jugador
	 * @return
	 */
	Integer calcularCheckins(Jugador jugador);
	
	/**
	 * @param jugador
	 * @return
	 */
	Integer calcularPremios(Jugador jugador);

	/**
	 * @param jugador
	 * @param cazaTesoro
	 * @return
	 */
	boolean tienePremio(Jugador jugador, CazaTesoro cazaTesoro);

	/**
	 * @param imei
	 * @return
	 */
	Jugador recuperarPorImei(String imei);

}
