package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.exception.StockageException;

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
	public abstract void execute() throws StockageException, Exception;

}
