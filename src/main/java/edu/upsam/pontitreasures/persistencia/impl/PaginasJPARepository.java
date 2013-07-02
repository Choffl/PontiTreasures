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
import edu.upsam.pontitreasures.dominio.PaginaJuego;
import edu.upsam.pontitreasures.persistencia.PaginasRepository;

/**
 * @author ssabariego
 *
 */
@Repository
public class PaginasJPARepository implements PaginasRepository {
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Collection<PaginaJuego> recuperarTodas() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PaginaJuego> criteriaQuery = criteriaBuilder.createQuery(PaginaJuego.class);
		Root<PaginaJuego> root = criteriaQuery.from(PaginaJuego.class);
		
		criteriaQuery.select(root);
		 
		TypedQuery<PaginaJuego> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
	@Override
	public PaginaJuego recuperarPorId(Long paginaId) {
		return entityManager.find(PaginaJuego.class, paginaId);
	}

	@Override
	public void agregar(PaginaJuego paginaJuego) {
		entityManager.persist(paginaJuego);
	}

	@Override
	public PaginaJuego recuperaUnicoPor(String propiedad, String valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PaginaJuego> criteriaQuery = criteriaBuilder.createQuery(PaginaJuego.class);
		Root<PaginaJuego> root = criteriaQuery.from(PaginaJuego.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(propiedad), valor));
		 
		TypedQuery<PaginaJuego> typedQuery = entityManager.createQuery(criteriaQuery);
		List<PaginaJuego> resultados = typedQuery.getResultList();
		return resultados.isEmpty()?null:resultados.get(0);
	}

}
