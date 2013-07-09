/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.dominio.TipoPagina;
import edu.upsam.pontitreasures.persistencia.PaginasRepository;
import edu.upsam.pontitreasures.servicios.PaginasServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class PaginasServicioImpl implements PaginasServicio {
	
	@Autowired
	private PaginasRepository paginasRepository;

	/* (non-Javadoc)
	 * @see edu.upsam.pontitreasures.servicios.PaginasServicio#recuperarTodas()
	 */
	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public Collection<PaginaJuego> recuperarTodas() {
		return paginasRepository.recuperarTodas();
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Collection<PaginaJuego> recuperarPorTipo(TipoPagina tipo) {
		return paginasRepository.recuperaPor("tipoPagina", tipo);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public PaginaJuego recuperarPorId(Long paginaId) {
		return paginasRepository.recuperarPorId(paginaId);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public PaginaJuego alta(String nombre, String descripcion, byte[] paginaHtml) {
		PaginaJuego paginaJuego = new PaginaJuego(nombre,descripcion);
		paginaJuego.setPaginaHtml(paginaHtml);
		paginaJuego.setTipoPagina(TipoPagina.PERSONALIZABLE);
		paginasRepository.agregar(paginaJuego);
		return paginaJuego;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public PaginaJuego recuperaPaginaCheckinIncorrecto() {
		return paginasRepository.recuperaUnicoPor("nombre","Checkin incorrecto");
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void actualizar(PaginaJuego paginaJuego) {
		paginasRepository.actualizar(paginaJuego);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public boolean eliminar(Long paginaId) {
		boolean eliminado = Boolean.FALSE;
		PaginaJuego paginaJuego = paginasRepository.recuperarPorId(paginaId);
		if(esEliminable(paginaJuego)){
			paginasRepository.eliminar(paginaJuego);
			eliminado = Boolean.TRUE;
		}
		return eliminado;
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public PaginaJuego recuperarPaginaPorTipo(TipoPagina tipoPagina) {
		return paginasRepository.recuperaUnicoPor("tipoPagina", tipoPagina);
	}

	private boolean esEliminable(PaginaJuego paginaJuego) {
		if(!paginasRepository.esUsadaCazas(paginaJuego) && !paginasRepository.esUsadaEtiquetas(paginaJuego)){
			return true;
		}
		return false;
	}

}
