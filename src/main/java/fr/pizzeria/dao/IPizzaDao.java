package fr.pizzeria.dao;

import java.sql.SQLException;
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

	List<Pizza> findAllPizzas();

	/**
	 * @param pizza
	 * @throws SavePizzaException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void saveNewPizza(Pizza pizza) throws SavePizzaException, SQLException, ClassNotFoundException;

	/**
	 * @param codePizza
	 * @param pizza
	 * @throws UpdatePizzaException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException, ClassNotFoundException, SQLException;

	/**
	 * @param codePizza
	 * @throws DeletePizzaException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void deletePizza(String codePizza) throws DeletePizzaException, ClassNotFoundException, SQLException;

	/**
	 * @param codePizza
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	boolean verifierExistence(String codePizza) throws StockageException, SQLException, ClassNotFoundException;
}
