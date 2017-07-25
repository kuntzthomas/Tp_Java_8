package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.ihm.menu.option.ConnectionClientOptionMenu;
import fr.pizzeria.ihm.menu.option.InscriptionOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterMenu;

public class MenuLogging implements IMenu {

	private static final Logger LOG = LoggerFactory.getLogger(MenuAdmin.class);
	private static final int NUMERO_OPTION_MENU = 99;

	private String titre;
	private IClientDao dao;
	private Scanner scanner;
	private Map<Integer, OptionMenu> optionMenu = new TreeMap<>();

	public MenuLogging(IClientDao dao, Scanner scanner, String titre) {

		this.titre = titre;
		this.scanner = scanner;
		this.dao = dao;
		initMenu(dao);
	}

	private void initMenu(IClientDao dao) {
		optionMenu.put(1, new InscriptionOptionMenu(dao));
		optionMenu.put(2, new ConnectionClientOptionMenu(dao));
		optionMenu.put(NUMERO_OPTION_MENU, new QuitterMenu());

	}

	@Override
	public void afficher() {
		LOG.info(titre);
		optionMenu.forEach((numero, action) -> LOG.info(numero + ". " + action.getLibelle()));
	}

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
