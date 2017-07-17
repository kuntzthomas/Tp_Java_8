package fr.pizzeria.model;

/**
 * @author Thomas
 * 
 *         Génere les catégories de pizza
 */
public enum CategoriePizza {

	VIANDE("Viande"), FROMAGE("Fromage"), VEGETARIENNE("Végétarienne");

	String libelle;

	CategoriePizza(String libelle) {
		this.setLibelle(libelle);

	}

	public String getLibelle() {
		return libelle;
	}

	void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
