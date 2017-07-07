package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoMemoire;

public class Menu {

	static Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	static OptionMenu[] optionMenu;

	public Menu() {

		optionMenu = new OptionMenu[4];
		optionMenu[0] = new ListerPizzasOptionMenu();
		optionMenu[1] = new AjouterPizzaOptionMenu();
		optionMenu[2] = new ModifierPizzaOptionMenu();
		optionMenu[3] = new SupprimerPizzaOptionMenu();
	}

	public void afficher() {

		System.out.println("***** Pizzeria Administration *****");

		for (int i = 0; i < optionMenu.length; i++) {
			System.out.println(optionMenu[i].getLibelle());
		}
		System.out.println("99. Sortie.");
	}

	public void manage() throws Exception {

		PizzaDaoMemoire dao = new PizzaDaoMemoire();
		
		int choixMenu = 0;

		do {

			afficher();

			choixMenu = questionUser.nextInt();
			switch (choixMenu) {

			case 1:

				optionMenu[0].execute(dao);
				break;

			case 2:

				optionMenu[1].execute(dao);
				break;

			case 3:

				optionMenu[0].execute(dao);
				optionMenu[2].execute(dao);
				break;

			case 4:

				
				optionMenu[3].execute(dao);
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
