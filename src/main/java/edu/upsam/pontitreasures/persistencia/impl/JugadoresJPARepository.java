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

import edu.upsam.pontitreasures.dominio.Jugador;
import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.JugadoresRepository;

@Repository(value="jugadoresRepository")
public class JugadoresJPARepository implements JugadoresRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregar(Jugador jugador) {
		entityManager.persist(jugador);
	}

	@Override
	public void actualizar(Jugador jugador) {
		entityManager.merge(jugador);
	}

	@Override
	public Jugador recuperarPorId(Long id) {
		return entityManager.find(Jugador.class, id);
	}

	@Override
	public Jugador recuperarUnicoPor(String propiedad, Object valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Jugador> criteriaQuery = criteriaBuilder.createQuery(Jugador.class);
		Root<Jugador> root = criteriaQuery.from(Jugador.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(propiedad), valor));
		 
		TypedQuery<Jugador> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Jugador> resultados = typedQuery.getResultList();
		return resultados.isEmpty()?null:resultados.get(0);
	}

	@Override
	public Collection<Jugador> recuperarPor(String propiedad, Object valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Jugador> criteriaQuery = criteriaBuilder.createQuery(Jugador.class);
		Root<Jugador> root = criteriaQuery.from(Jugador.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(propiedad), valor));
		 
		TypedQuery<Jugador> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}


}
