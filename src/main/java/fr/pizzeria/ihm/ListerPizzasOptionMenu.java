package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;

public class ListerPizzasOptionMenu extends OptionMenu {

	public String getLibelle() {

		return "1. Liste des Pizzas";
	}

	@Override
	public boolean execute(IPizzaDao dao) {

		for (int i = 0; i < dao.findAllPizzas().size(); i++) {

			if (dao.findAllPizzas() != null) {

				System.out.print(dao.findAllPizzas().get(i).getCode() + " -> " + dao.findAllPizzas().get(i).getNom() + " ("
						+ dao.findAllPizzas().get(i).getPrix() + ")");

				System.out.println(" ");
			}

		}

		System.out.println(" ");
		return false;
	}
}
