package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;

/**
 * @author Thomas
 * 
 * Menu gérant la suppression d'une pizza
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	Scanner questionUser = new Scanner(System.in).useLocale(Locale.US);

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#getLibelle()
	 */
	@Override
	public String getLibelle() {

		return "4. Supprimer une pizza";
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute(fr.pizzeria.dao.IPizzaDao)
	 */
	@Override
	public boolean execute(IPizzaDao dao) {

		System.out.println("Veuillez saisir le code");

		String codePizza = null;
		boolean codeTrouve = false;
		do {
			codePizza = questionUser.next();
			try {
				dao.verifierExistence(codePizza);
				codeTrouve = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				codeTrouve = false;
			}
		} while (!codeTrouve);

		if (!codePizza.equals("99")) {

			try {
				dao.deletePizza(codePizza);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Pizza supprimée");
			System.out.println("");
		}
		return false;
	}
}
