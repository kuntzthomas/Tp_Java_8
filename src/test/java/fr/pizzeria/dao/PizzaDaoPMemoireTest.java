package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoPMemoireTest {

	PizzaDaoMemoire pizzaDaoMemoire;
	List<Pizza> listePizza;

	@Before
	public void setUp() throws Exception {

		pizzaDaoMemoire = new PizzaDaoMemoire();
		listePizza = new ArrayList<>();
		listePizza.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromage", 12.00, CategoriePizza.FROMAGE));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));

	}

	@Test
	public void testFindAllPizzas() {

		assertThat(listePizza).containsAll(this.pizzaDaoMemoire.findAllPizzas());
	}

	@Test
	public void testSaveNewPizza() throws Exception {
		Pizza pizza = new Pizza("CAL", "calzone", 15, CategoriePizza.VIANDE);
		pizzaDaoMemoire.saveNewPizza(pizza);
		assertThat(this.pizzaDaoMemoire.findAllPizzas()).contains(pizza);

	}

	@Test
	public void testUpdatePizza() throws Exception {

		Pizza pizza = new Pizza("CAL", "calzone", 15, CategoriePizza.VIANDE);
		pizzaDaoMemoire.updatePizza("MAR", pizza);
		assertThat(this.pizzaDaoMemoire.findAllPizzas()).contains(pizza);

	}

	@Test
	public void testDeletePizza() throws Exception {

		Pizza pizza = new Pizza("MAR", "Margherita", 14.00, CategoriePizza.VIANDE);
		pizzaDaoMemoire.deletePizza("MAR");
		assertThat(this.pizzaDaoMemoire.findAllPizzas()).doesNotContainSubsequence(pizza);
	}

	@Test
	public void testVerifierExistenceException() {

	}

	@Test
	public void testVerifierExistence() throws Exception {

	}
}
