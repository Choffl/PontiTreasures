/**
 * 
 */
package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

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

	/**
	 * @param cazaTesoro
	 */
	void actualizar(CazaTesoro cazaTesoro);

	/**
	 * @param cazaId
	 * @return
	 */
	boolean eliminar(Long cazaId);

	/**
	 * @param cazaId
	 */
	void activar(Long cazaId);

	/**
	 * @param cazaId
	 */
	void cerrar(Long cazaId);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Integer calcularCheckinsAnonimos(CazaTesoro cazaTesoro);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Integer calcularCheckinsIdentificados(CazaTesoro cazaTesoro);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Integer calcularPremiosAnonimos(CazaTesoro cazaTesoro);

	/**
	 * @param cazaTesoro
	 * @return
	 */
	Integer calcularPremiosIdentificados(CazaTesoro cazaTesoro);

}
