package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
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
public class MenuAdmin implements IMenu {

	private static final Logger LOG = LoggerFactory.getLogger(MenuAdmin.class);
	private static final int NUMERO_OPTION_MENU = 99;

	private String titre;
	private IPizzaDao dao;
	private Scanner scanner;
	private Map<Integer, OptionMenu> optionMenu = new TreeMap<>();

	public MenuAdmin(IPizzaDao dao, Scanner scanner, String titre) {

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
		optionMenu.put(NUMERO_OPTION_MENU, new QuitterMenu());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.menu.IMenu#afficher()
	 */
	@Override
	public void afficher() {

		LOG.info(titre);
		optionMenu.forEach((numero, action) -> LOG.info(numero + ". " + action.getLibelle()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.menu.IMenu#manage()
	 */
	@Override
	public void manage() throws Exception {

		int choixMenu = 0;

		do {

			afficher();
			choixMenu = scanner.nextInt();
			optionMenu.get(choixMenu).execute();

		} while (choixMenu != 99);
	}
}
