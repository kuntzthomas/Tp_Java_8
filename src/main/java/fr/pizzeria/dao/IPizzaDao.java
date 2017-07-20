package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * @author Thomas
 *
 */
public interface IPizzaDao {

	void initPizza();

	List<Pizza> findAllPizzas();

	/**
	 * @param pizza
	 * @throws SavePizzaException
	 */
	void saveNewPizza(Pizza pizza) throws SavePizzaException;

	/**
	 * @param codePizza
	 * @param pizza
	 * @throws UpdatePizzaException
	 */
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	/**
	 * @param codePizza
	 * @throws DeletePizzaException
	 */
	void deletePizza(String codePizza) throws DeletePizzaException;

	/**
	 * @param codePizza
	 * @return
	 * @throws Exception
	 */
	boolean verifierExistence(String codePizza) throws StockageException;
}
