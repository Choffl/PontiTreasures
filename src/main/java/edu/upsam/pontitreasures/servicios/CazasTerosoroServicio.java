/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import java.util.Collection;
import java.util.Date;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.Usuario;

/**
 * @author ssabariego
 *
 */
public interface CazasTerosoroServicio {

	/**
	 * @return todos las instancias de juegos
	 */
	Collection<CazaTesoro> recuperarTodos();

	/**
	 * @param nombre
	 * @param fechaInicio
	 * @param fechaFin
	 * @param circuito
	 * @param gestor
	 * @return
	 */
	CazaTesoro alta(String nombre, Date fechaInicio, Date fechaFin, Circuito circuito, Usuario gestor);

}
