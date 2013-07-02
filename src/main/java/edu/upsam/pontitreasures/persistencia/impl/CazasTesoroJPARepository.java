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
import edu.upsam.pontitreasures.dominio.Circuito;
import edu.upsam.pontitreasures.dominio.Jugador;
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


	@Override
	public Collection<CazaTesoro> recuperarPor(String propiedad, Object valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CazaTesoro> criteriaQuery = criteriaBuilder.createQuery(CazaTesoro.class);
		Root<CazaTesoro> root = criteriaQuery.from(CazaTesoro.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(propiedad), valor));
		 
		TypedQuery<CazaTesoro> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


	@Override
	public CazaTesoro recuperarPorId(Long id) {
		return entityManager.find(CazaTesoro.class, id);
	}


	@Override
	public void agregar(CazaTesoro cazaTesoro) {
		 entityManager.persist(cazaTesoro);
	}

}
