package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoire implements IPizzaDao {

	public Pizza[] listePizza = new Pizza[50];

	public PizzaDaoMemoire() {

		listePizza[0] = new Pizza(0, "PEP", "Pépéroni", 12.50);
		listePizza[1] = new Pizza(1, "MAR", "Margherita", 14.00);
		listePizza[2] = new Pizza(2, "REI", "La Reine", 11.50);
		listePizza[3] = new Pizza(3, "FRO", "La 4 fromage", 12.00);
		listePizza[4] = new Pizza(4, "CAN", "La cannibale", 12.50);
		listePizza[5] = new Pizza(5, "SAV", "La savoyarde", 13.00);
		listePizza[6] = new Pizza(6, "ORI", "L'orientale", 13.50);
		listePizza[7] = new Pizza(7, "IND", "L'indienne", 14.00);
	}

	public Pizza[] findAllPizzas() {

		return listePizza;
	}

	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException{

		for (int i = 0; i < listePizza.length; i++) {

			if (listePizza[i] == null) {

				listePizza[i] = new Pizza(i, pizza.getCode(), pizza.getNom(), pizza.getPrix());
				System.out.println("Pizza ajoutée");
				System.out.println("");
				break;
			}
		}
		return false;
	}

	public boolean updatePizza(String codeUpDate, Pizza pizza) throws UpdatePizzaException{

		for (int i = 0; i < listePizza.length; i++) {

			if (listePizza[i] != null && codeUpDate.equals(listePizza[i].getCode())) {

				listePizza[i].setCode(pizza.getCode());
				listePizza[i].setNom(pizza.getNom());
				listePizza[i].setPrix(pizza.getPrix());

			}
		}
		System.out.println("Pizza mise à jour");
		System.out.println("");
		return false;
	}

	public void verifierExistence(String codePizza) throws UpdatePizzaException {

		boolean trouve = false;
		for (int i = 0; i < findAllPizzas().length; i++) {

			if (findAllPizzas()[i] != null && codePizza.equals(findAllPizzas()[i].getCode())) {

				trouve = true;
			}
		}
		if (!trouve) {
			throw new UpdatePizzaException("Le code " + codePizza + " n'existe pas");
		}

	}

	public boolean deletePizza(String codeUpDate) throws DeletePizzaException{

		for (int i = 0; i < listePizza.length; i++) {

			if (listePizza[i] != null && codeUpDate.equals(listePizza[i].getCode())) {

				listePizza[i] = null;
				break;
			}
		}
		return false;
	}

}
