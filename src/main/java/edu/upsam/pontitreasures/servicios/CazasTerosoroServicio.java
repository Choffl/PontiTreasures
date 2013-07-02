/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import java.util.Collection;
import java.util.Date;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
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
	 * @param condicionMencion 
	 * @param condicionPremio 
	 * @param paginaPremioIdentificado 
	 * @param paginaPremioAnonimo 
	 * @return
	 */
	void alta(String nombre, Circuito circuito, Usuario gestor, PaginaJuego paginaPremioAnonimo, PaginaJuego paginaPremioIdentificado, Integer condicionPremio, Integer condicionMencion);
	
	/**
	 * @return
	 */
	Collection<CazaTesoro> recuperarActivas();
	
	/**
	 * @param id
	 * @return
	 */
	CazaTesoro recuperarPorId(Long id);

}
