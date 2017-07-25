package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.StockageException;

public class QuitterMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);

	@Override
	public String getLibelle() {
		return "Sortie.";
	}

	@Override
	public void execute() throws StockageException {

		LOG.info("Aurevoir !");
	}
}
