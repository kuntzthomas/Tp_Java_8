package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;

public abstract class OptionMenu {

	protected String libelle;

	// protected IPizzaDao = dao;
	//
	// public OptionMenu(listePizza){
	// super();
	// this.dao = dao;
	// }

	public abstract String getLibelle();

	public abstract boolean execute(IPizzaDao dao) throws Exception;
}
