package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * @author Thomas
 *
 */
public abstract class OptionMenu {

	protected String libelle;

	/**
	 * @return
	 */
	public abstract String getLibelle();

	/**
	 * @param dao
	 * @return
	 * @throws StockageException
	 * @throws Exception
	 */
	public abstract boolean execute(IPizzaDao dao) throws StockageException;

}
