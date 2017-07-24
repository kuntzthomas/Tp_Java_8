package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJpa.class);

	EntityManagerFactory emf;
	EntityManager em;

	public PizzaDaoJpa() {
		this.emf = Persistence.createEntityManagerFactory("Pizza");
	}

	private Pizza findByCode(String codePizza) {
		Pizza p = em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza)
				.getSingleResult();
		return p;
	}

	@Override
	public List<Pizza> findAllPizzas() {
		em = emf.createEntityManager();
		List<Pizza> query = em.createNamedQuery("pizza.findAll", Pizza.class).getResultList();
		em.close();
		return query;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException, SQLException, ClassNotFoundException {

		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza)
			throws UpdatePizzaException, ClassNotFoundException, SQLException {

		em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza p = findByCode(codePizza);
		p.setNom(pizza.getNom());
		p.setCategoriePizza(pizza.getCategoriePizza());
		p.setPrix(pizza.getPrix());
		em.merge(p);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException, ClassNotFoundException, SQLException {

		em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza p = findByCode(codePizza);
		em.remove(p);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public boolean verifierExistence(String codePizza) throws StockageException, SQLException, ClassNotFoundException {

		em = emf.createEntityManager();
		try {
			em.createNamedQuery("pizza.findByCode", Pizza.class).setParameter("codePizza", codePizza).getSingleResult();
			em.close();
			return true;
		} catch (NoResultException e) {
			LOG.debug("Error la pizza existe déjà", e);
			return false;
		}
	}

}
