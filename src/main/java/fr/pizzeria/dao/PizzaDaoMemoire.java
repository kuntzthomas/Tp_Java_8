package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Thomas
 *
 */
public class PizzaDaoMemoire implements IPizzaDao {

	private List<Pizza> listePizza = new ArrayList<>();
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoire.class);

	/**
	 * Initalise les pizzas
	 */
	public void initPizza() {

		LOG.info("Initialisation des pizzas");

		listePizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromage", 12.00, CategoriePizza.FROMAGE));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));

		LOG.info("pizzas initialissé");
	}

	/*
	 * {@link IPizzaDao}
	 */
	public List<Pizza> findAllPizzas() {

		LOG.info("Récupératin des pizzas");
		return new ArrayList<>(listePizza);
	}

	/*
	 * {@link IPizzaDao}
	 */
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {

		listePizza.stream().filter(p -> p.getCode().equals(pizza.getCode())).findAny()
				.orElseThrow(() -> new SavePizzaException("Erreur : le code e la pizza existe déjà. Pizza non sauvée"));
		listePizza.add(pizza);

	}

	/*
	 * {@link IPizzaDao}
	 */
	public void updatePizza(String codeUpdate, Pizza pizza) throws UpdatePizzaException {

		for (Pizza p : listePizza) {

			if (p.getCode().equals(codeUpdate)) {

				listePizza.get(p.getId()).setCode(pizza.getCode());

				listePizza.get(p.getId()).setNom(pizza.getNom());

				listePizza.get(p.getId()).setPrix(pizza.getPrix());

				listePizza.get(p.getId()).setCategoriePizza(pizza.getCategoriePizza());

			}
		}
	}

	/*
	 * {@link IPizzaDao}
	 */
	public boolean verifierExistence(String codePizza) throws StockageException {

		for (int i = 0; i < findAllPizzas().size(); i++) {

			if (codePizza.equals(findAllPizzas().get(i).getCode())) {

				return true;

			}
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public void deletePizza(String codeUpdate) throws DeletePizzaException {

		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codeUpdate.equals(listePizza.get(i).getCode())) {

				listePizza.remove(i);
				break;
			}
		}
	}

}
