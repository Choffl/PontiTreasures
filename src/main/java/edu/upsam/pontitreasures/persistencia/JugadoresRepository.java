package edu.upsam.pontitreasures.persistencia;

import java.util.Collection;

import edu.upsam.pontitreasures.dominio.Jugador;


public interface JugadoresRepository extends UsuariosRepository<Jugador>{

	/**
	 * @return
	 */
	Collection<Jugador> recuperarTodos();

	/**
	 * @param generarHash
	 * @return
	 */
	Jugador recuperarPorIdentificador(String generarHash);
	
	

}
