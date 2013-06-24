package edu.upsam.pontitreasures.servicios;

import edu.upsam.pontitreasures.dominio.Usuario;



/**
 * @author ssabariego
 *
 */
public interface AutenticacionServicio {

	/**
	 * @param email
	 * @param password
	 * @return
	 */
	Usuario autenticar(String email, String password);

}
