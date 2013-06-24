package edu.upsam.pontitreasures.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.UsuariosRepository;
import edu.upsam.pontitreasures.servicios.AutenticacionServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class AutenticacionServicioImpl implements AutenticacionServicio{
	
	@Autowired
	@Qualifier("usuariosRepository")
	private UsuariosRepository<Usuario> usuariosRepository;

	/**
	 * @see edu.upsam.pontitreasures.servicios.AutenticacionServicio#autenticar(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario autenticar(String email, String password) {
		Usuario usuario = usuariosRepository.recuperarUnicoPor("email", email);
		//TODO: Null object patron
		if(usuario != null && usuario.getPassword().equals(password)){
			return usuario;
		}
		return null;
	}

}
