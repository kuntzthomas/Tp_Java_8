package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCTest {

	private static PizzaDaoJDBC pizzaDaoJDBC;
	private static List<Pizza> listePizza;
	private static Connection connection;

	@BeforeClass
	public static void intialisation() throws Exception {

		pizzaDaoJDBC = new PizzaDaoJDBC("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "", "org.h2.Driver");

		pizzaDaoJDBC.initPizza();

		listePizza = new ArrayList<>();

		listePizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));

	}

	@Test
	public void testFindAllPizza() throws Exception {

		assertThat(listePizza).containsAll(pizzaDaoJDBC.findAllPizzas());

	}

	@Test
	public void testSaveNewPizza() throws Exception {

		pizzaDaoJDBC.saveNewPizza(new Pizza("CAL", "Calzone", 12.5d, CategoriePizza.VIANDE));
		connection = pizzaDaoJDBC.createConnection();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("Select * from Pizza");
		while (result.next()) {
			assertThat(result.getString("code")).isIn("PEP", "MAR", "REI", "FRO", "CAN", "SAV", "ORI", "CAL");
		}
		result.close();

	}

	@Test
	public void testUpdatePizza() throws UpdatePizzaException, ClassNotFoundException, SQLException {

		connection = pizzaDaoJDBC.createConnection();
		pizzaDaoJDBC.updatePizza("SAV", new Pizza("VAS", "Vas", 9.0, CategoriePizza.VIANDE));
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("Select * from Pizza where code like 'VAS';");
		while (result.next()) {
			assertThat(result.getString("Nom")).contains("Vas");

		}

	}

	@Test
	public void testDeletePizza() throws DeletePizzaException, ClassNotFoundException, SQLException {
		connection = pizzaDaoJDBC.createConnection();
		pizzaDaoJDBC.deletePizza("SAV");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("Select * from Pizza");
		while (result.next()) {
			assertThat(result.getString("Nom")).doesNotContain("Vas");

		}
	}

}
