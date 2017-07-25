package fr.pizzeria.ihm.menu;

import fr.pizzeria.dao.exception.StockageException;

public interface IMenu {

	/**
	 * Affichage de la liste des pizzas
	 */
	void afficher();

	/**
	 * Lance la génération du menu puis l'aiguillage dans celui-ci
	 * 
	 * @throws StockageException
	 * 
	 * @throws Exception
	 */
	void manage() throws Exception;

}