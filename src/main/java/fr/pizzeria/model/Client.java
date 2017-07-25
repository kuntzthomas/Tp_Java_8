package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "client.findAll", query = "SELECT c FROM Client c"),
		@NamedQuery(name = "client.acces", query = "select c from Client c where c.email=:email and c.mdp=:mdp") })
public class Client {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String nom;

	@Column
	private String prenom;

	@Column
	private String email;

	@Column(name = "mot_de_passe")
	private String mdp;

	public Client() {
		super();
	}

	public Client(String nom, String prenom, String email, String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return mdp;
	}

	public void setMotDePasse(String mdp) {
		this.mdp = mdp;
	}

}
