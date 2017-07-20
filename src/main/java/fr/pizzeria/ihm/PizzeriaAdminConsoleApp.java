package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.ihm.menu.Menu;

/**
 * @author Thomas
 * 
 * 
 */
public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws Exception {

		IPizzaDao dao = new PizzaDaoMemoire();
		dao.initPizza();
		try (Scanner scanner = new Scanner(System.in)) {
			Menu menu = new Menu(dao, scanner, "***** Pizzeria Administration *****");
			menu.manage();
		}
	}

}
