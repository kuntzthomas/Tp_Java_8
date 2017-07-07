package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	
	@Override
	public String getLibelle() {

		return "2. Ajouter une nouvelle pizza";
	}

	@Override
	public boolean execute(IPizzaDao dao) {


		System.out.println("Veuillez saisir le code de la pizza");
		String code = questionUser.next();

		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = questionUser.next();

		System.out.println("Veuillez saisir le prix");
		double prix = questionUser.nextDouble();

		Pizza pizza = new Pizza(code, nom, prix);
		try {
			dao.saveNewPizza(pizza);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
}
