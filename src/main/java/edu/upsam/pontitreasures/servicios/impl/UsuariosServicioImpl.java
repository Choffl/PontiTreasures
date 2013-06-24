/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.upsam.pontitreasures.dominio.TipoUsuario;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.UsuariosRepository;
import edu.upsam.pontitreasures.servicios.UsuariosServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class UsuariosServicioImpl implements UsuariosServicio {
	
	@Autowired
	@Qualifier("usuariosRepository")
	private UsuariosRepository<Usuario> usuariosRepository;

	
	@Override
	public Collection<Usuario> recuperarGestores() {
		return usuariosRepository.recuperarPor("tipo", TipoUsuario.GESTOR);
	}

	@Override
	public Usuario recuperarPorId(Long gestorId) {
		return usuariosRepository.recuperarPorId(gestorId);
	}

}
