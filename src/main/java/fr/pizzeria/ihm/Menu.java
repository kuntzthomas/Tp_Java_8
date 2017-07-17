package fr.pizzeria.ihm;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;

/**
 * @author Thomas
 *
 */
public class Menu {

	static Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	static Map<Integer, OptionMenu> optionMenu = new HashMap<Integer, OptionMenu>();
	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);

	public Menu() {

		optionMenu.put(0, new ListerPizzasOptionMenu());
		optionMenu.put(1, new AjouterPizzaOptionMenu());
		optionMenu.put(2, new ModifierPizzaOptionMenu());
		optionMenu.put(3, new SupprimerPizzaOptionMenu());

	}

	/**
	 * Affichage de la liste des pizzas
	 */
	public void afficher() {

		LOG.info("***** Pizzeria Administration *****");

		for (int i = 0; i < optionMenu.size(); i++) {
			LOG.info((optionMenu.get(i)).getLibelle());
		}
		LOG.info("99. Sortie.");
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
				LOG.info("Aurevoir");
				break;

			default:
				LOG.info("Veuillez saisir un numero de menu valide");
			}

		} while (choixMenu != 99);

	}
}
