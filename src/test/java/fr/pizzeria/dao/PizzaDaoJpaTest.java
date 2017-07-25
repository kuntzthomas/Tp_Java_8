package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpaTest {

	private static PizzaDaoJpa pizzaDaoJpa;
	private static List<Pizza> listePizza;
	EntityManager em;

	@BeforeClass
	public static void intialisation() throws Exception {

		pizzaDaoJpa = new PizzaDaoJpa();
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
	public void findAllPizzaTest() {

		assertThat(listePizza).containsAll(pizzaDaoJpa.findAllPizzas());
	}

	public void testSaveNewPizza() throws Exception {

		pizzaDaoJpa.saveNewPizza(new Pizza("CAL", "Calzone", 12.5d, CategoriePizza.VIANDE));
		List<Pizza> result = em.createNamedQuery("pizza.findAll", Pizza.class).getResultList();
		assertThat(result).containsAll(pizzaDaoJpa.findAllPizzas());
	}
}
