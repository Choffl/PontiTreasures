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

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.persistencia.CazasTesoroRepository;

/**
 * @author ssabariego
 *
 */
@Repository
public class CazasTesoroJPARepository implements CazasTesoroRepository {
	

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Collection<CazaTesoro> recuperarTodos() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CazaTesoro> criteriaQuery = criteriaBuilder.createQuery(CazaTesoro.class);
		Root<CazaTesoro> root = criteriaQuery.from(CazaTesoro.class);
		
		criteriaQuery.select(root);
		 
		TypedQuery<CazaTesoro> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
