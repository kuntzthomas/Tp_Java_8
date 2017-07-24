package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.menu.Menu;

/**
 * @author Thomas
 * 
 * 
 */
public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws Exception {

		IPizzaDao dao = new PizzaDaoJpa();
		try (Scanner scanner = new Scanner(System.in)) {
			Menu menu = new Menu(dao, scanner, "***** Pizzeria Administration *****");
			menu.manage();
		}
	}

}
