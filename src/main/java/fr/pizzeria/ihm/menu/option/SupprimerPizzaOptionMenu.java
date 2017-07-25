package fr.pizzeria.ihm.menu.option;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.StockageException;

/**
 * @author Thomas
 * 
 *         Menu gérant la suppression d'une pizza
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	Scanner scanner;
	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);
	private IPizzaDao dao;

	public SupprimerPizzaOptionMenu(IPizzaDao dao) {

		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {

		return "Supprimer une pizza";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public void execute() throws StockageException, ClassNotFoundException, SQLException {

		LOG.info("Veuillez saisir le code");

		String codePizza = null;
		codePizza = scanner.next();
		while (!dao.verifierExistence(codePizza)) {
			codePizza = scanner.next();
		}

		if (!"99".equals(codePizza)) {

			try {
				dao.deletePizza(codePizza);
			} catch (Exception e) {
				LOG.info(e.getMessage());
				LOG.debug("Error", e);
			}

			LOG.info("Pizza supprimée");
		}
	}
}
