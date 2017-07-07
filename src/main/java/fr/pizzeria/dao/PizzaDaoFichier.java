package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFichier implements IPizzaDao {

	public PizzaDaoFichier() {
		// TODO Auto-generated constructor stub
	}

	public Pizza[] findAllPizzas() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deletePizza(String codePizza) throws DeletePizzaException{
		// TODO Auto-generated method stub
		return false;
	}

	public void verifierExistence(String codePizza) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
