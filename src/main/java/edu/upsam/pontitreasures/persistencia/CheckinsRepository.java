package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Checkin;
import edu.upsam.pontitreasures.dominio.Etiqueta;

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

}
