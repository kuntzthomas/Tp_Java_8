package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {

	Scanner scanner;
	private static final Logger LOG = LoggerFactory.getLogger(ModifierPizzaOptionMenu.class);
	private IPizzaDao dao;

	public ModifierPizzaOptionMenu(IPizzaDao dao) {

		this.dao = dao;
	}

	@Override
	public String getLibelle() {

		return "Mettre à jour une pizza";
	}

	@Override
	public boolean execute() throws StockageException, ClassNotFoundException, SQLException {

		LOG.info("Veuillez saisir le code");
		LOG.info("(99 pour abandonner)");

		String codePizza = null;
		codePizza = scanner.next();
		while (!dao.verifierExistence(codePizza)) {
			codePizza = scanner.next();
		}

		if (!"99".equals(codePizza)) {

			LOG.info("Veuillez saisir le nouveau code");
			String code = scanner.next();

			LOG.info("Veuillez saisir le nom (sans espace)");
			String nom = scanner.next();

			LOG.info("Veuillez saisir le prix");
			double prix = scanner.nextDouble();

			int i = 0;
			for (CategoriePizza categories : CategoriePizza.values()) {
				String categ = categories.name();
				LOG.info("{} {}", i, categ);
				i++;
			}

			LOG.info("Veuillez saisir la catégorie de la pizza");
			String categoriePizza = scanner.next();

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
				LOG.info(e.getMessage());
				LOG.debug("Error", e);
			}

		}
		return false;
	}
}
