package edu.upsam.pontitreasures.persistencia.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import edu.upsam.pontitreasures.dominio.Checkin;
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

}
