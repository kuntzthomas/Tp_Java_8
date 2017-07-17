package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Thomas
 *
 */
public class PizzaDaoMemoire implements IPizzaDao {

	List<Pizza> listePizza = new ArrayList<Pizza>();
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);

	public PizzaDaoMemoire() {

		listePizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromage", 12.00, CategoriePizza.FROMAGE));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizzas()
	 */
	public List<Pizza> findAllPizzas() {

		return listePizza;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#saveNewPizza(fr.pizzeria.model.Pizza)
	 */
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {

		listePizza.add(new Pizza(pizza.getCode(), pizza.getNom(), pizza.getPrix(), pizza.getCategoriePizza()));
		LOG.info("Pizza ajoutée");

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String,
	 * fr.pizzeria.model.Pizza)
	 */
	public boolean updatePizza(String codeUpDate, Pizza pizza) throws UpdatePizzaException {

		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codeUpDate.equals(listePizza.get(i).getCode())) {

				listePizza.get(i).setCode(pizza.getCode());
				listePizza.get(i).setNom(pizza.getNom());
				listePizza.get(i).setPrix(pizza.getPrix());
				listePizza.get(i).setCategoriePizza(pizza.getCategoriePizza());

			}
		}
		LOG.info("Pizza mise à jour");
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#verifierExistence(java.lang.String)
	 */
	public void verifierExistence(String codePizza) throws UpdatePizzaException {

		boolean trouve = false;
		for (int i = 0; i < findAllPizzas().size(); i++) {

			if (findAllPizzas().get(i) != null && codePizza.equals(findAllPizzas().get(i).getCode())) {

				trouve = true;
			}
		}
		if (!trouve) {
			throw new UpdatePizzaException("Le code " + codePizza + " n'existe pas");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public boolean deletePizza(String codeUpDate) throws DeletePizzaException {

		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codeUpDate.equals(listePizza.get(i).getCode())) {

				listePizza.remove(i);
				break;
			}
		}
		return false;
	}

}
