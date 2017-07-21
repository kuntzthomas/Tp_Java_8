package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoDabtabaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);
	IPizzaDao pizzaDaoDatabase;
	List<Pizza> listePizza;
	Statement statement;
	static Connection connection;
	PreparedStatement insertPizza;
	static final String H2_DRIVER = "org.h2.Driver";
	static final String DB_URL = "jdbc:h2:mem:testdb";
	static final String USER = "sa";
	static final String PASS = "";

	@BeforeClass
	public static void init() throws Exception {
		try {
			Class.forName(H2_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			LOG.debug("ERROR: Class not found: " + e.getMessage());
		}
	}

	@Before
	public void setUp() throws Exception {

		LOG.info("Creating table in database...");
		statement = connection.createStatement();

		statement.execute("CREATE TABLE `PIZZA` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`code` varchar(10) NOT NULL, `nom` varchar(255) NOT NULL,`prix` double NOT NULL,"
				+ "`categorie` varchar(255) NOT NULL);");

		listePizza = new ArrayList<>();
		listePizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		for (Pizza pizza : listePizza) {
			insertPizza = connection
					.prepareStatement("INSERT INTO pizza(code, nom, prix, categorie) VALUES (?, ?, ?, ?);");
			insertPizza.setString(1, pizza.getCode());
			insertPizza.setString(2, pizza.getNom());
			insertPizza.setDouble(3, pizza.getPrix());
			insertPizza.setString(4, pizza.getCategoriePizza().getLibelle());
			insertPizza.executeUpdate();
		}

		LOG.info("Table created");
	}

	@Test
	public void testInitPizza() throws Exception {
		ResultSet result = statement.executeQuery("SELECT * FROM PIZZA");
		while (result.next()) {
			assertThat(result.getString("code")).isIn("PEP", "MAR", "REI", "FRO", "CAN", "SAV", "ORI", "IND");
			assertThat(result.getString("nom")).isIn("Pépéroni", "Margherita", "La Reine", "La 4 fromages",
					"La cannibale", "La savoyarde", "L'orientale", "L'indienne");
			assertThat(result.getDouble("prix")).isIn(12.50, 14.00, 11.50, 12.00, 12.50, 13.00, 13.50, 14.00);
			assertThat(result.getString("categorie")).isIn("Viande", "Viande", "Viande", "Fromage", "Viande", "Viande",
					"Viande", "Viande");
		}
	}

	public void testFindAllPizza() throws Exception {

		assertThat(listePizza).containsAll(this.pizzaDaoDatabase.findAllPizzas());

	}

	public void testSaveNewPizza() throws Exception {
		Pizza pizza = new Pizza("CAL", "calzone", 15, CategoriePizza.VIANDE);
		pizzaDaoDatabase.saveNewPizza(pizza);
		assertThat(pizzaDaoDatabase.findAllPizzas()).contains(pizza);

	}

	@Test
	public void testUpdatePizza() throws UpdatePizzaException {

	}

	@Test
	public void testDeletePizza() throws DeletePizzaException {

	}

	// @Test
	// public boolean testVerifierExistence() throws StockageException {
	// return true;
	// }

	@After
	public void tearDown() throws Exception {
		statement.execute("DROP TABLE PIZZA");
		connection.close();
		LOG.info("Table deleted");
	}

}
