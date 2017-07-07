package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoMemoire;

/**
 * @author Thomas
 *
 */
public class Menu {

	static Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	static Map<Integer, OptionMenu> optionMenu = new HashMap<Integer, OptionMenu>();
	
	public Menu() {

		optionMenu.put(0, new ListerPizzasOptionMenu());
		optionMenu.put(1, new AjouterPizzaOptionMenu());
		optionMenu.put(2, new ModifierPizzaOptionMenu());
		optionMenu.put(3, new SupprimerPizzaOptionMenu());
		
	}

	/**
	 *  Affichage de la liste des pizzas
	 */
	public void afficher() {

		System.out.println("***** Pizzeria Administration *****");

		for (int i = 0; i < optionMenu.size(); i++) {
			System.out.println((optionMenu.get(i)).getLibelle());
		}
		System.out.println("99. Sortie.");
	}

	/**
	 * Lance la génération du menu puis l'aiguillage dans celui-ci
	 * 
	 * @throws Exception
	 */
	public void manage() throws Exception {

		PizzaDaoMemoire dao = new PizzaDaoMemoire();
		
		int choixMenu = 0;

		do {

			afficher();

			choixMenu = questionUser.nextInt();
			switch (choixMenu) {

			case 1:
				
				optionMenu.get(0).execute(dao);
				break;

			case 2:

				optionMenu.get(1).execute(dao);
				break;

			case 3:

				optionMenu.get(0).execute(dao);
				optionMenu.get(2).execute(dao);
				break;

			case 4:

				optionMenu.get(3).execute(dao);
				break;

			case 99:
				System.out.println("Aurevoir");
				break;

			default:
				System.out.println("Veuillez saisir un numero de menu valide");
			}

		} while (choixMenu != 99);

	}
}
