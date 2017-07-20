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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriePizza == null) ? 0 : categoriePizza.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (categoriePizza != other.categoriePizza)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}

}