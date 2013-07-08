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


	@Override
	public void actualizar(CazaTesoro cazaTesoro) {
		entityManager.merge(cazaTesoro);		
	}


	@Override
	public void eliminar(CazaTesoro cazaTesoro) {
		entityManager.remove(cazaTesoro);
	}


	@Override
	public boolean tieneJugadores(CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Jugador> criteriaQuery = criteriaBuilder.createQuery(Jugador.class);
		Root<Jugador> root = criteriaQuery.from(Jugador.class);

		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cazaTesoroActiva"), cazaTesoro));

		TypedQuery<Jugador> typedQuery = entityManager.createQuery(criteriaQuery);
		return !typedQuery.getResultList().isEmpty();
	}

	@Override
	public boolean tieneCheckins(CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Checkin> resultados = typedQuery.getResultList();

		return !resultados.isEmpty();
	}
}

