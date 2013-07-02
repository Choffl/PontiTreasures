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


import edu.upsam.pontitreasures.dominio.Usuario;
import edu.upsam.pontitreasures.persistencia.UsuariosRepository;

/**
 * @author ssabariego
 *
 */
@Repository(value="usuariosRepository")
public class UsuariosJPARepository implements UsuariosRepository<Usuario>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregar(Usuario usuario) {
		entityManager.persist(usuario);
	}

	@Override
	public void actualizar(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Override
	public Usuario recuperarPorId(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public Usuario recuperarUnicoPor(String propiedad, Object valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(propiedad), valor));
		 
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Usuario> resultados = typedQuery.getResultList();
		return resultados.isEmpty()?null:resultados.get(0);
	}

	@Override
	public Collection<Usuario> recuperarPor(String propiedad, Object valor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(propiedad), valor));
		 
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
	

}
