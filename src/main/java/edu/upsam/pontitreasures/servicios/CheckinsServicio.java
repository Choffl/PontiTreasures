package edu.upsam.pontitreasures.servicios;

import edu.upsam.pontitreasures.dominio.PaginaJuego;

public interface CheckinsServicio {
	
	/**
	 * @param identificadorJugador
	 * @param codigoQR
	 * @param cazaId
	 * @param latitud
	 * @param longitud
	 */
	PaginaJuego registroCheckin(String identificadorJugador, String codigoQR, Long cazaId, String latitud, String longitud);

}
