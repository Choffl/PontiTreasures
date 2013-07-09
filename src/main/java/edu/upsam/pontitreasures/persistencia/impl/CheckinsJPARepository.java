package edu.upsam.pontitreasures.persistencia.impl;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.upsam.pontitreasures.dominio.CazaTesoro;
import edu.upsam.pontitreasures.dominio.Checkin;
import edu.upsam.pontitreasures.dominio.Etiqueta;
import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.persistencia.CheckinsRepository;

/**
 * @author ssabariego
 *
 */
@Repository
public class CheckinsJPARepository implements CheckinsRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregar(Checkin checkin) {
		entityManager.persist(checkin);
	}
	
	@Override
	public Collection<Checkin> recuperarCheckinsAnonimos(Etiqueta etiqueta) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("etiqueta"), etiqueta),criteriaBuilder.isNull(root.get("jugador").get("email"))));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarCheckinsIdentificados(Etiqueta etiqueta) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("etiqueta"), etiqueta),criteriaBuilder.isNotNull(root.get("jugador").get("email"))));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarCheckinsAnonimos(CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro),criteriaBuilder.isNull(root.get("jugador").get("email"))));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarCheckinsIdentificados(CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro),criteriaBuilder.isNotNull(root.get("jugador").get("email"))));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarPremiosAnonimos(CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro),
				criteriaBuilder.isNull(root.get("jugador").get("email")), 
				criteriaBuilder.equal(root.get("premio"), Boolean.TRUE)));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarPremiosIdentificados(CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro),
				criteriaBuilder.isNotNull(root.get("jugador").get("email")), 
				criteriaBuilder.equal(root.get("premio"), Boolean.TRUE)));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarCheckins(Jugador jugador) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("jugador"), jugador));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarPremios(Jugador jugador) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("jugador"), jugador),
				criteriaBuilder.equal(root.get("premio"), Boolean.TRUE)));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> buscarCheckinPor(Etiqueta etiqueta, Jugador jugador, CazaTesoro cazaTesoro, Date fechaActual) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("jugador"), jugador),
						criteriaBuilder.equal(root.get("etiqueta"), etiqueta),
				criteriaBuilder.equal(root.get("fecha"), fechaActual)));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarCheckinsCaza(Jugador jugador, CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("jugador"), jugador),
				criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro)));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperarCheckinsDistintosCaza(Jugador jugador, CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("jugador"), jugador),
				criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro))).groupBy(root.get("etiqueta")).distinct(true);

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	@Override
	public Collection<Checkin> recuperaPremios(Jugador jugador,	CazaTesoro cazaTesoro) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checkin> criteriaQuery = criteriaBuilder.createQuery(Checkin.class);
		Root<Checkin> root = criteriaQuery.from(Checkin.class);

		criteriaQuery.select(root).where(criteriaBuilder.and
				(criteriaBuilder.equal(root.get("jugador"), jugador),
				criteriaBuilder.equal(root.get("cazaTesoro"), cazaTesoro),		
				criteriaBuilder.equal(root.get("premio"), Boolean.TRUE)));

		TypedQuery<Checkin> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

}
