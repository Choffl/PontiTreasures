package edu.upsam.pontitreasures.servicios;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.dominio.TipoPagina;


/**
 * @author ssabariego
 *
 */
public interface PaginasServicio {

	/**
	 * @return
	 */
	Collection<PaginaJuego> recuperarTodas();

	/**
	 * @param paginaId
	 * @return
	 */
	PaginaJuego recuperarPorId(Long paginaId);

	/**
	 * @param nombre
	 * @param descripcion
	 * @param ficheroHtml
	 * @return
	 */
	PaginaJuego alta(String nombre, String descripcion, byte[] ficheroHtml);

	/**
	 * @return
	 */
	PaginaJuego recuperaPaginaCheckinIncorrecto();

	/**
	 * @param paginaJuego
	 */
	void actualizar(PaginaJuego paginaJuego);

	/**
	 * @param paginaId
	 * @return
	 */
	boolean eliminar(Long paginaId);

	/**
	 * @param personalizable
	 * @return
	 */
	Collection<PaginaJuego> recuperarPorTipo(TipoPagina tipo);

}
