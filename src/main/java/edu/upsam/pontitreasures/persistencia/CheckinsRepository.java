package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;
import java.util.Date;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Checkin;
import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.Jugador;

public interface CheckinsRepository {
	
	/**
	 * @param checkin
	 */
	void agregar(Checkin checkin);
	
	/**
	 * @param etiqueta
	 * @return
	 */
	Collection<Checkin> recuperarCheckinsAnonimos(Etiqueta etiqueta);

	/**
	 * @param etiqueta
	 * @return
	 */
	Collection<Checkin> recuperarCheckinsIdentificados(Etiqueta etiqueta);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Collection<Checkin> recuperarCheckinsAnonimos(CazaTesoro cazaTesoro);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Collection<Checkin> recuperarCheckinsIdentificados(CazaTesoro cazaTesoro);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Collection<Checkin> recuperarPremiosAnonimos(CazaTesoro cazaTesoro);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Collection<Checkin> recuperarPremiosIdentificados(CazaTesoro cazaTesoro);

	/**
	 * @param jugador
	 * @return
	 */
	Collection<Checkin> recuperarCheckins(Jugador jugador);

	/**
	 * @param jugador
	 * @return
	 */
	Collection<Checkin> recuperarPremios(Jugador jugador);

	/**
	 * @param etiqueta
	 * @param jugador
	 * @param cazaTesoro
	 * @param fechaActual
	 * @return
	 */
	Collection<Checkin> buscarCheckinPor(Etiqueta etiqueta, Jugador jugador, CazaTesoro cazaTesoro, Date fechaActual);

	/**
	 * @param jugador
	 * @return
	 */
	Collection<Checkin> recuperarCheckinsCaza(Jugador jugador, CazaTesoro cazaTesoro);

	/**
	 * @param jugador
	 * @return
	 */
	Collection<Checkin> recuperarCheckinsDistintosCaza(Jugador jugador, CazaTesoro cazaTesoro);

	/**
	 * @param jugador
	 * @param cazaTesoro
	 * @return
	 */
	Collection<Checkin> recuperaPremios(Jugador jugador, CazaTesoro cazaTesoro);

}
