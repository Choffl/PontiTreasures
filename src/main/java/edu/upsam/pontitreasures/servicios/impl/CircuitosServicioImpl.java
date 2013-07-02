/**
 * 
 */
package edu.upsam.pontitreasures.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.persistencia.CircuitosRepository;
import edu.upsam.pontitreasures.servicios.CircuitosServicio;
import edu.upsam.pontitreasures.servicios.EtiquetasServicio;

/**
 * @author ssabariego
 *
 */
@Service
public class CircuitosServicioImpl implements CircuitosServicio {
	
	@Autowired
	private CircuitosRepository circuitosRepository;
	
	@Autowired
	private EtiquetasServicio etiquetasServicio;

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Collection<Circuito> recuperarTodos() {
		return circuitosRepository.recuperarTodos();
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Circuito recuperarPorId(Long circuitoId) {
		return circuitosRepository.recuperarPorId(circuitoId);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public void alta(String nombre, String descripcion, Collection<Long> etiquetas) {
		Circuito circuito = crearCircuito(nombre, descripcion, etiquetas);
		circuitosRepository.agrega(circuito);	
	}

	private Circuito crearCircuito(String nombre, String descripcion, Collection<Long> etiquetas) {
		Circuito circuito = new Circuito();
		circuito.setNombre(nombre);
		circuito.setDescripcion(descripcion);
		establecerEtiquetas(circuito, etiquetas);
		return circuito;
	}

	private void establecerEtiquetas(Circuito circuito,	Collection<Long> etiquetas) {
		for (Long id : etiquetas) {
			circuito.getEtiquetas().add(etiquetasServicio.recuperarPorId(id));
		}
		
	}

}
