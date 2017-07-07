package fr.pizzeria.model;

/**
 * @author Thomas
 *
 */
public class Pizza {

	int id;
	String code;
	String nom;
	double prix;
	static int compteurId;
	CategoriePizza categoriePizza;

	public Pizza() {

	}

	/**
	 * @param id
	 * @param code
	 * @param nom
	 * @param prix
	 * @param categoriePizza
	 */
	public Pizza(int id, String code, String nom, double prix, CategoriePizza categoriePizza) {

		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categoriePizza = categoriePizza;
	}

	/**
	 * @param code
	 * @param nom
	 * @param prix
	 * @param categoriePizza
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza categoriePizza) {

		this.id = compteurId++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categoriePizza = categoriePizza;
	}

	public String toString(String code, String nom, double prix, CategoriePizza categoriePizza) {

		String str = this.code + " -> " + this.nom + " (" + this.prix + ") " + this.categoriePizza;
		return str;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategoriePizza() {
		return categoriePizza;
	}

	public void setCategoriePizza(CategoriePizza categoriePizza) {
		this.categoriePizza = categoriePizza;
	}

}