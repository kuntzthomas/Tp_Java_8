package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.ihm.menu.IMenu;
import fr.pizzeria.ihm.menu.MenuLogging;

public class PizzeriaClientConsoleApp {

	public static void main(String[] args) throws Exception {

		IClientDao dao = new ClientDaoJpa();
		try (Scanner scanner = new Scanner(System.in)) {
			IMenu menu = new MenuLogging(dao, scanner, "***** Pizzeria Client *****");
			menu.manage();
		}
	}
}
