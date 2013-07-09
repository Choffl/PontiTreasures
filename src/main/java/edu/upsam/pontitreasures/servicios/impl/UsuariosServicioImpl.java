/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Collection<Usuario> recuperarGestores() {
		return usuariosRepository.recuperarPor("tipo", TipoUsuario.GESTOR);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Usuario recuperarPorId(Long gestorId) {
		return usuariosRepository.recuperarPorId(gestorId);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void alta(String username, String email, String password, TipoUsuario tipoUsuario) {
		Usuario gestor = new Usuario(email,password,username,tipoUsuario);
		usuariosRepository.agregar(gestor);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void actualizar(Usuario usuario) {
		usuariosRepository.actualizar(usuario);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public boolean eliminar(Long gestorId) {
		boolean eliminado = Boolean.FALSE;
		Usuario usuario = usuariosRepository.recuperarPorId(gestorId);
		if(esEliminable(usuario)){
			usuariosRepository.eliminar(usuario);
			eliminado = Boolean.TRUE;
		}
		return eliminado;
	}

	private boolean esEliminable(Usuario usuario) {
		if(!usuariosRepository.gestionaCazas(usuario)){
			return true;
		}
		return false;
	}

}
