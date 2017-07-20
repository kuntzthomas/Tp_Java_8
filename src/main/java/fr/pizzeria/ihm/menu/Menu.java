package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.ihm.menu.option.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.menu.option.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

/**
 * @author Thomas
 *
 */
public class Menu {

	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	private static final int NUMERO_OPTION_MENU = 99;

	private String titre;
	private IPizzaDao dao;
	private Scanner scanner;
	private Map<Integer, OptionMenu> optionMenu = new TreeMap<>();

	public Menu(IPizzaDao dao, Scanner scanner, String titre) {

		this.titre = titre;
		this.scanner = scanner;
		this.dao = dao;
		initMenu(dao);
	}

	private void initMenu(IPizzaDao dao) {
		optionMenu.put(1, new ListerPizzasOptionMenu(dao));
		optionMenu.put(2, new AjouterPizzaOptionMenu(dao));
		optionMenu.put(3, new ModifierPizzaOptionMenu(dao));
		optionMenu.put(4, new SupprimerPizzaOptionMenu(dao));
		optionMenu.put(NUMERO_OPTION_MENU, new QuitterMenu(dao));

	}

	/**
	 * Affichage de la liste des pizzas
	 */
	public void afficher() {

		optionMenu.forEach((numero, action) -> LOG.info(numero + ". " + action.getLibelle()));
	}

	/**
	 * Lance la génération du menu puis l'aiguillage dans celui-ci
	 * 
	 * @throws Exception
	 */
	public void manage() throws StockageException {

		int choixMenu = 0;

		do {

			afficher();

			choixMenu = scanner.nextInt();

			optionMenu.get(choixMenu).execute();

		} while (choixMenu != 99);

	}
}
