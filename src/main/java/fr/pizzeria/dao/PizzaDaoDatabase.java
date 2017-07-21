package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoDatabase implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);
	Statement statement;
	static Connection connection;
	PreparedStatement insertPizza;
	static final String JDBC_DRIVER = "org.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/pizza";
	static final String USER = "root";
	static final String PASS = "";
	List<Pizza> listePizza;

	public PizzaDaoDatabase() {

	}

	@Override
	public void initPizza() throws Exception {

		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
		statement = connection.createStatement();
	}

	@Override
	public List<Pizza> findAllPizzas() throws Exception {
		listePizza = new ArrayList<>();
		ResultSet result = statement.executeQuery("SELECT * FROM PIZZA");
		LOG.info("requete effectuée");
		while (result.next()) {
			int id = result.getInt("id");
			String code = result.getString("code");
			String nom = result.getString("nom");
			double prix = result.getDouble("prix");
			int categoriePizza = result.getInt("categorie");
			Pizza pizza = new Pizza(id, code, nom, prix, CategoriePizza.values()[categoriePizza]);
			listePizza.add(pizza);
		}
		LOG.info("Récupération des pizzas");
		return listePizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {

	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {

	}

	@Override
	public boolean verifierExistence(String codePizza) throws StockageException {
		return true;
	}

}
