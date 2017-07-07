package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;

public class ListerPizzasOptionMenu extends OptionMenu {

	public String getLibelle() {

		return "1. Liste des Pizzas";
	}

	@Override
	public boolean execute(IPizzaDao dao) {

		for (int i = 0; i < dao.findAllPizzas().length; i++) {

			if (dao.findAllPizzas()[i] != null) {

				System.out.print(dao.findAllPizzas()[i].getCode() + " -> " + dao.findAllPizzas()[i].getNom() + " ("
						+ dao.findAllPizzas()[i].getPrix() + ")");

				System.out.println(" ");
			}

		}

		System.out.println(" ");
		return false;
	}
}
