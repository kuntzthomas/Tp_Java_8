package fr.pizzeria.ihm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.IMenu;
import fr.pizzeria.ihm.menu.MenuAdmin;

public class MenuTest {

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	private IMenu menu;
	private IPizzaDao dao;
	private Scanner scanner;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void TestMenu() {

	}

	@Test
	public void TestAfficher() {

		menu = new MenuAdmin(dao, scanner, "***** Pizzeria Administration *****");
		this.menu.afficher();

		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("***** Pizzeria Administration *****");
		assertThat(logConsole).contains("1. Liste des Pizzas");
		assertThat(logConsole).contains("2. Ajouter une nouvelle pizza");
		assertThat(logConsole).contains("3. Mettre à jour une pizza");
		assertThat(logConsole).contains("4. Supprimer une pizza");
		assertThat(logConsole).contains("99. Sortie.");

	}

}