/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.persistencia.EtiquetasRepository;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class EtiquetasServicioImpl implements EtiquetasServicio {
	
	@Autowired
	private EtiquetasRepository etiquetasRepository;


	@Override
	public Collection<Etiqueta> recuperarTodas() {
		return etiquetasRepository.recuperarTodas();
	}


	@Override
	public Etiqueta recuperarPorId(Long etiquetaId) {
		return etiquetasRepository.recuperarPorId(etiquetaId);
	}


	@Override
	public byte[] recuperarImagenQR(Long etiquetaId) {
		Etiqueta etiqueta = recuperarPorId(etiquetaId);
		return QRCode.from(etiqueta.getCodigo()).to(ImageType.PNG).withSize(500, 500).stream().toByteArray();
	}

}
