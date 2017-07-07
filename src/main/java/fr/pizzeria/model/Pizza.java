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
	
	public Pizza(){
		
	}
	
	/**
	 * Constructeur Pizza
	 * 
	 * @param id
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public Pizza(int id, String code, String nom, double prix){
		this.id = id ;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		
	}
	
	public Pizza(String code, String nom, double prix){
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		
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
	
	
}