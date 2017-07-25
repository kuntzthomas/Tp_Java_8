package fr.pizzeria.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Client;

public class ClientDaoJpa implements IClientDao {

	private static final Logger LOG = LoggerFactory.getLogger(ClientDaoJpa.class);

	EntityManagerFactory emf;
	EntityManager em;

	public void saveNew(Client client) throws SQLException, ClassNotFoundException {

		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Client verifieAcces(String email, String mdp) {
		em = emf.createEntityManager();
		try {
			Client client = em.createNamedQuery("client.acces", Client.class).setParameter("email", email)
					.setParameter("mdp", mdp).getSingleResult();
			em.close();
			return client;
		} catch (NoResultException e) {
			LOG.debug("Les informations de connections sont erronées", e);
			return null;
		}
	}
}
