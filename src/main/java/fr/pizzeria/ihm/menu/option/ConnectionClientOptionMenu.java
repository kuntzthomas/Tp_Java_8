package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.ihm.menu.IMenu;
import fr.pizzeria.ihm.menu.MenuClient;

public class ConnectionClientOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ConnectionClientOptionMenu.class);
	private Scanner scanner;
	private IClientDao dao;

	public ConnectionClientOptionMenu(IClientDao dao) {
		this.dao = dao;
	}

	@Override
	public String getLibelle() {
		return "Connection";
	}

	@Override
	public void execute() throws Exception {

		LOG.info("Veuillez saisir votre email");
		String email = scanner.next();

		LOG.info("Veuillez saisir votre mot de passe");
		String mdp = scanner.next();

		if (!dao.verifieAcces(email, mdp).equals(null)) {
			IMenu menu = new MenuClient(dao, scanner, "***** Pizzeria Client *****");
			menu.manage();
		}
	}
}
