package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(ModifierPizzaOptionMenu.class);

	@Override
	public String getLibelle() {

		return "3. Mettre à jour une pizza";
	}

	@Override
	public boolean execute(IPizzaDao dao) throws StockageException {

		LOG.info("Veuillez saisir le code");
		LOG.info("(99 pour abandonner)");

		String codePizza = null;
		boolean codeTrouve = false;

		do {
			codePizza = questionUser.next();
			try {
				dao.verifierExistence(codePizza);
				codeTrouve = true;
			} catch (UpdatePizzaException e) {
				LOG.info(e.getMessage());
				codeTrouve = false;
			}
		} while (!codeTrouve);

		if (!"99".equals(codePizza)) {

			LOG.info("Veuillez saisir le nouveau code");
			String code = questionUser.next();

			LOG.info("Veuillez saisir le nom (sans espace)");
			String nom = questionUser.next();

			LOG.info("Veuillez saisir le prix");
			double prix = questionUser.nextDouble();

			int i = 0;
			for (CategoriePizza categories : CategoriePizza.values()) {
				String categ = categories.name();
				LOG.info("{} {}", i, categ);
				i++;
			}

			LOG.info("Veuillez saisir la catégorie de la pizza");
			String categoriePizza = questionUser.next();

			for (CategoriePizza categories : CategoriePizza.values()) {
				if (LevenshteinDistance.getDefaultInstance().apply(categories.name(), categoriePizza) <= 2) {
					String categ = categories.name();
					LOG.info(categ);
				}
			}

			Pizza pizza = new Pizza(code, nom, prix, CategoriePizza.valueOf(categoriePizza));

			try {
				dao.updatePizza(codePizza, pizza);
				LOG.info("Pizza modifiée");
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			}

		}
		return false;
	}
}
