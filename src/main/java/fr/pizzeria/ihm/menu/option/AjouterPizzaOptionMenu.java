package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Thomas
 *
 *         Menu gérant l'ajout d'une nouvelle pizza
 *
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	private Scanner scanner;
	private IPizzaDao dao;

	public AjouterPizzaOptionMenu(IPizzaDao dao) {

		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {

		return "Ajouter une nouvelle pizza";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public void execute() {

		LOG.info("Veuillez saisir le code de la pizza");
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
			dao.saveNewPizza(pizza);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			LOG.debug("Error", e);
		}
		LOG.info("Pizza ajoutée");
	}
}
