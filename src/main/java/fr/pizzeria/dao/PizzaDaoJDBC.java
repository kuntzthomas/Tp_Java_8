package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.StockageException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBC implements IPizzaDao {

	private static final String CREATE_TABLE = "CREATE TABLE `Pizza` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
			+ "`code` varchar(10) NOT NULL, `nom` varchar(255) NOT NULL,`prix` double NOT NULL,"
			+ "`categorie` varchar(255) NOT NULL);";
	private static final String SAVE_PIZZA = "Insert into Pizza(code, nom, prix, categorie) Values (?,?,?,?)";
	private static final String UPDATE_PIZZA = "Update Pizza Set Nom = ?, Prix = ?, Categorie = ? where code like ?";
	private static final String DELETE_PIZZA = "DELETE FROM Pizza where code like ?";
	private static final String FIND_PIZZA = "Select * from Pizza where code like ?";
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBC.class);

	private String url;
	private String user;
	private String pass;

	public PizzaDaoJDBC(String url, String user, String pwd, String driver) throws ClassNotFoundException {
		this.url = url;
		this.user = user;
		this.pass = pwd;
		Class.forName(driver);

	}

	public Connection createConnection() throws SQLException {

		return DriverManager.getConnection(url, user, pass);

	}

	public void initPizza() throws SQLException {

		try (Connection connection = createConnection()) {

			createTablePizza(connection);

			List<Pizza> listePizza = new ArrayList<>();
			listePizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.FROMAGE));
			listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
			listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));

			insertListPizzas(listePizza, connection);
			connection.close();
			LOG.info("Table created");
		}

	}

	private void insertListPizzas(List<Pizza> listePizza, Connection connection) throws SQLException {
		for (Pizza pizza : listePizza) {
			try (PreparedStatement insertPizza = connection.prepareStatement(SAVE_PIZZA)) {
				insertPizza.setString(1, pizza.getCode());
				insertPizza.setString(2, pizza.getNom());
				insertPizza.setDouble(3, pizza.getPrix());
				insertPizza.setString(4, pizza.getCategoriePizza().getLibelle());
				insertPizza.executeUpdate();
			}
		}
	}

	private void createTablePizza(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
			statement.execute();
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {

		List<Pizza> listePizza = new ArrayList<>();
		try (Connection connection = createConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(FIND_PIZZA)) {
			while (result.next()) {
				int id = result.getInt("id");
				String code = result.getString("code");
				String nom = result.getString("nom");
				double prix = result.getDouble("prix");
				int categoriePizza = result.getInt("categorie");
				listePizza.add(new Pizza(id, code, nom, prix, CategoriePizza.values()[categoriePizza]));
				connection.close();
				result.close();
			}
		} catch (SQLException e) {
			LOG.debug("Error recherche pizza", e);
		}
		return listePizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException, SQLException, ClassNotFoundException {

		try (Connection saveConnection = createConnection();
				PreparedStatement savePizza = saveConnection.prepareStatement(SAVE_PIZZA)) {
			savePizza.setString(1, pizza.getCode());
			savePizza.setString(2, pizza.getNom());
			savePizza.setDouble(3, pizza.getPrix());
			savePizza.setInt(4, pizza.getCategoriePizza().ordinal());
			savePizza.execute();
			saveConnection.close();
			savePizza.close();
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza)
			throws UpdatePizzaException, ClassNotFoundException, SQLException {

		try (Connection updateConnection = createConnection();
				PreparedStatement updatePizza = updateConnection.prepareStatement(UPDATE_PIZZA);) {
			updatePizza.setString(1, pizza.getNom());
			updatePizza.setDouble(2, pizza.getPrix());
			updatePizza.setInt(3, pizza.getCategoriePizza().ordinal());
			updatePizza.setString(4, pizza.getCode());
			updatePizza.execute();
			updatePizza.close();
			updateConnection.close();
		}
	}

	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException, ClassNotFoundException, SQLException {

		try (Connection deleteConnection = createConnection();
				PreparedStatement deletePizza = deleteConnection.prepareStatement(DELETE_PIZZA)) {
			deletePizza.setString(1, codePizza);
			deletePizza.execute();
			deletePizza.close();
			deleteConnection.close();
		}
	}

	@Override
	public boolean verifierExistence(String codePizza) throws StockageException, SQLException, ClassNotFoundException {

		try (Connection connection = createConnection();
				PreparedStatement findPizza = connection.prepareStatement(FIND_PIZZA);
				ResultSet result = findPizza.executeQuery();) {
			findPizza.setString(1, codePizza);
			boolean exist = result.first();
			result.close();
			findPizza.close();
			return exist;
		}
	}

}
