package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	Pizza[] findAllPizzas();

	boolean saveNewPizza(Pizza pizza) throws SavePizzaException;

	boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	boolean deletePizza(String codePizza) throws DeletePizzaException;

	void verifierExistence(String codePizza) throws Exception;
}
