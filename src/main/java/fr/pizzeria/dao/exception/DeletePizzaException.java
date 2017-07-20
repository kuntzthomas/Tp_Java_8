package fr.pizzeria.dao.exception;

/**
 * @author Thomas
 *
 */
public class DeletePizzaException extends StockageException {

	public DeletePizzaException() {
	}

	public DeletePizzaException(String message) {
		super(message);
	}
}