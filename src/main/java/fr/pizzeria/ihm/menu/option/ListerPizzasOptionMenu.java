package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;

/**
 * @author Thomas
 * 
 *         Menu gérant l'affichage de la liste des pizzas
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);

	private IPizzaDao dao;

	public ListerPizzasOptionMenu(IPizzaDao dao) {

		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	public String getLibelle() {

		return "Liste des Pizzas";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute() throws Exception {

		for (int i = 0; i < dao.findAllPizzas().size(); i++) {

			if (dao.findAllPizzas() != null) {

				LOG.info("{} -> {} : ({}€) {}", dao.findAllPizzas().get(i).getCode(),
						dao.findAllPizzas().get(i).getNom(), dao.findAllPizzas().get(i).getPrix(),
						dao.findAllPizzas().get(i).getCategoriePizza().getLibelle());
			}

		}

		return false;
	}
}
