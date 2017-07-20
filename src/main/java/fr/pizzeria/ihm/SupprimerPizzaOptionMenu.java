package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author Thomas
 * 
 *         Menu gérant la suppression d'une pizza
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);
	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {

		return "4. Supprimer une pizza";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) throws StockageException {

		LOG.info("Veuillez saisir le code");

		String codePizza = null;
		codePizza = questionUser.next();
		while (!dao.verifierExistence(codePizza)) {
			codePizza = questionUser.next();
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
		return false;
	}
}
