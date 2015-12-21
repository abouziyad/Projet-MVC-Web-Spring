package rdvmedecin.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Personne extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(length = 20)
	private String nom;
	@Column(length = 20)
	private String prenom;
	@Column(length = 5)
	private String titre;
	
	public Personne(){
		super();
	}

	public Personne(String titre, String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.titre = titre;
	}
	
	public String toString(){
		
		return String.format("[Personne : %s %s %s %s , %s]",id,version,titre, nom,prenom);
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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	

}
