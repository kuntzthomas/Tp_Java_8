package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.text.similarity.LevenshteinDistance;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);

	@Override
	public String getLibelle() {

		return "3. Mettre à jour une pizza";
	}

	@Override
	public boolean execute(IPizzaDao dao) throws Exception {

		System.out.println("Veuillez saisir le code");
		System.out.println("(99 pour abandonner)");
		String codePizza = null;
		boolean codeTrouve = false;
		
		do {
			codePizza = questionUser.next();
			try {
				dao.verifierExistence(codePizza);
				codeTrouve = true;
			} catch (UpdatePizzaException e) {
				System.out.println(e.getMessage());
				codeTrouve = false;
			}
		} while (!codeTrouve);

		if (!codePizza.equals("99")) {

			System.out.println("Veuillez saisir le nouveau code");
			String code = questionUser.next();

			System.out.println("Veuillez saisir le nom (sans espace)");
			String nom = questionUser.next();

			System.out.println("Veuillez saisir le prix");
			double prix = questionUser.nextDouble();
			
			int i = 0;
			for (CategoriePizza categories : CategoriePizza.values()) {
				System.out.println(i + " " + categories.name());
				i++;
			}
			
			System.out.println("Veuillez saisir la catégorie de la pizza");
			String categoriePizza = questionUser.next();

			for (CategoriePizza categories : CategoriePizza.values()) {
				if (LevenshteinDistance.getDefaultInstance().apply(categories.name(), categoriePizza) <= 2)
					System.out.println(categories.name());
			}

			Pizza pizza = new Pizza(code, nom, prix, CategoriePizza.valueOf(categoriePizza));

			try {
				dao.updatePizza(codePizza, pizza);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Pizza modifiée");
			System.out.println("");

		}
		return false;
	}
}
