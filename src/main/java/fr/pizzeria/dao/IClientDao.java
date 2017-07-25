package fr.pizzeria.dao;

import java.sql.SQLException;

import fr.pizzeria.model.Client;

public interface IClientDao {

	public void saveNew(Client client) throws SQLException, ClassNotFoundException;

	public Client verifieAcces(String email, String mdp);

}
