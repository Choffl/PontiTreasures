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

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Checkin;
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

	@Override
	public void agrega(Circuito circuito) {
		entityManager.persist(circuito);
		
	}

	@Override
	public void actualizar(Circuito circuito) {
		entityManager.merge(circuito);
	}

	@Override
	public Circuito recuperarPorIdConEtiquetas(Long circuitoId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Circuito> criteriaQuery = criteriaBuilder.createQuery(Circuito.class);
		Root<Circuito> root = criteriaQuery.from(Circuito.class);
		root.fetch("etiquetas");
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), circuitoId));
		
		TypedQuery<Circuito> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getSingleResult();
	}

	@Override
	public boolean esUsadoCaza(Circuito circuito) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CazaTesoro> criteriaQuery = criteriaBuilder.createQuery(CazaTesoro.class);
		Root<CazaTesoro> root = criteriaQuery.from(CazaTesoro.class);

		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("circuito"), circuito));

		TypedQuery<CazaTesoro> typedQuery = entityManager.createQuery(criteriaQuery);
		List<CazaTesoro> resultados = typedQuery.getResultList();
		
		return !resultados.isEmpty();
	}

	@Override
	public void eliminar(Circuito circuito) {
		entityManager.remove(circuito);
	}

}
