/**
 * 
 */
package edu.upsam.pontitreasures.persistencia.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.EtiquetasRepository;

/**
 * @author ssabariego
 *
 */
@Repository
public class EtiquetasJPARepository implements EtiquetasRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Collection<Etiqueta> recuperarTodas() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Etiqueta> criteriaQuery = criteriaBuilder.createQuery(Etiqueta.class);
		Root<Etiqueta> root = criteriaQuery.from(Etiqueta.class);
		
		criteriaQuery.select(root);
		 
		TypedQuery<Etiqueta> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Etiqueta recuperarPorId(Long id) {
		return entityManager.find(Etiqueta.class, id);
	}

	@Override
	public void agregar(Etiqueta etiqueta) {
		entityManager.persist(etiqueta);		
	}

	@Override
	public void eliminar(Long etiquetaId) {		
		entityManager.remove(recuperarPorId(etiquetaId));	
	}

	@Override
	public boolean consultaAsociadoCircuito(Long etiquetaId) {
		return false;
	}

	@Override
	public Etiqueta recuperarUnicoPor(String campo, String valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Etiqueta> criteriaQuery = criteriaBuilder.createQuery(Etiqueta.class);
		Root<Etiqueta> root = criteriaQuery.from(Etiqueta.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(campo), valor));
		 
		TypedQuery<Etiqueta> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Etiqueta> resultados = typedQuery.getResultList();
		return resultados.isEmpty()?null:resultados.get(0);
		
	}

}
