package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.text.similarity.LevenshteinDistance;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Thomas
 *
 * Menu gérant l'ajout d'une nouvelle pizza
 *
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {

		return "2. Ajouter une nouvelle pizza";
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		System.out.println("Veuillez saisir le code de la pizza");
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
			dao.saveNewPizza(pizza);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return true;
	}
}
