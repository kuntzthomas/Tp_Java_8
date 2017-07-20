package fr.pizzeria.dao.exception;

/**
 * @author Thomas
 *
 */
public class SavePizzaException extends StockageException {

	public SavePizzaException() {
	}

	public SavePizzaException(String message) {
		super(message);
	}

}
