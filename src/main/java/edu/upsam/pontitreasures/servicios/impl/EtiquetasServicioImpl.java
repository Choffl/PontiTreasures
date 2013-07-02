/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.math.BigDecimal;
import java.util.Collection;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.persistencia.EtiquetasRepository;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;
import edu.upsam.pontitreasures.servicios.PaginasServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class EtiquetasServicioImpl implements EtiquetasServicio {
	
	@Autowired
	private EtiquetasRepository etiquetasRepository;



	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Collection<Etiqueta> recuperarTodas() {
		return etiquetasRepository.recuperarTodas();
	}


	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Etiqueta recuperarPorId(Long etiquetaId) {
		return etiquetasRepository.recuperarPorId(etiquetaId);
	}


	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public byte[] recuperarImagenQR(Long etiquetaId) {
		Etiqueta etiqueta = recuperarPorId(etiquetaId);
		return QRCode.from(etiqueta.getCodigo()).to(ImageType.PNG).withSize(500, 500).stream().toByteArray();
	}


	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void alta(String codigo, String descripcion, String latitud,	String longuitud, PaginaJuego paginaAnonimo, PaginaJuego paginaIdentificado) {
		BigDecimal latitudValor = new BigDecimal(latitud);
		BigDecimal longitudValor = new BigDecimal(longuitud);
		
		Etiqueta etiqueta = new Etiqueta(codigo, descripcion, latitudValor, longitudValor);
		etiqueta.setPaginaCheckinAnonimo(paginaAnonimo);
		etiqueta.setPaginaCheckinIdentificado(paginaIdentificado);
		etiquetasRepository.agregar(etiqueta);
	}


	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public Boolean eliminar(Long etiquetaId) {
		Boolean eliminada = Boolean.FALSE;
		if(!etiquetasRepository.consultaAsociadoCircuito(etiquetaId)){
			etiquetasRepository.eliminar(etiquetaId);
			eliminada = Boolean.TRUE;
		}
		return eliminada;
	}


	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Etiqueta recuperarPorCodigo(String codigoQR) {
		return etiquetasRepository.recuperarUnicoPor("codigo", codigoQR);
	}

}
