package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoire implements IPizzaDao {

	List<Pizza> listePizza = new ArrayList<Pizza>();
	
	public PizzaDaoMemoire() {

		listePizza.add(new Pizza("PEP", "Pépéroni", 12.50));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00));
		listePizza.add(new Pizza("REI", "La Reine", 11.50));
		listePizza.add(new Pizza("FRO", "La 4 fromage", 12.00));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50));
		listePizza.add(new Pizza("IND", "L'indienne", 14.00));

	}

	public List<Pizza> findAllPizzas() {

		return listePizza;
	}

	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {

				listePizza.add(new Pizza(pizza.getCode(), pizza.getNom(), pizza.getPrix()));
				System.out.println("Pizza ajoutée");
				System.out.println("");
		
		return false;
	}

	public boolean updatePizza(String codeUpDate, Pizza pizza) throws UpdatePizzaException {

		for (int i = 0; i < listePizza.size(); i++) {

			if (listePizza.get(i) != null && codeUpDate.equals(listePizza.get(i).getCode())) {

				listePizza.get(i).setCode(pizza.getCode());
				listePizza.get(i).setNom(pizza.getNom());
				listePizza.get(i).setPrix(pizza.getPrix());

			}
		}
		System.out.println("Pizza mise à jour");
		System.out.println("");
		return false;
	}

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
