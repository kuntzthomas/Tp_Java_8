package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJDBC;
import fr.pizzeria.ihm.menu.Menu;

/**
 * @author Thomas
 * 
 * 
 */
public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) throws Exception {

		IPizzaDao dao = new PizzaDaoJDBC("jdbc:h2:mem:testdb", "sa", "", "org.h2.Driver");
		dao.initPizza();
		try (Scanner scanner = new Scanner(System.in)) {
			Menu menu = new Menu(dao, scanner, "***** Pizzeria Administration *****");
			menu.manage();
		}
	}

}
