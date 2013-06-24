/**
 * 
 */
package edu.upsam.pontitreasures.persistencia.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.persistencia.CircuitosRepository;

/**
 * @author ssabariego
 *
 */
@Repository
public class CircuitosJPARepository implements CircuitosRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Collection<Circuito> recuperarTodos() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Circuito> criteriaQuery = criteriaBuilder.createQuery(Circuito.class);
		Root<Circuito> root = criteriaQuery.from(Circuito.class);
		
		criteriaQuery.select(root);
		 
		TypedQuery<Circuito> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Circuito recuperarPorId(Long circuitoId) {
		return entityManager.find(Circuito.class, circuitoId);
	}

}
