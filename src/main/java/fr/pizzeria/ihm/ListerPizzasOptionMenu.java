package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;

/**
 * @author Thomas
 * 
 *         Menu gérant l'affichage de la liste des pizzas
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	public String getLibelle() {

		return "1. Liste des Pizzas";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		for (int i = 0; i < dao.findAllPizzas().size(); i++) {

			if (dao.findAllPizzas() != null) {

				String str = dao.findAllPizzas().get(i).getCode()
						.concat(dao.findAllPizzas().get(i).getNom())
						.concat(" (").concat(Double.toString(dao.findAllPizzas().get(i).getPrix())).concat(") ")
						.concat(dao.findAllPizzas().get(i).getCategoriePizza().getLibelle());

				System.out.print(str);

				System.out.println(" ");
			}

		}

		System.out.println(" ");
		return false;
	}
}
