package rdvmedecin.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class Client extends Personne {
	private static final long serialVersionUID = 1L;
	
	public Client(){
		super();
	}
	
	public Client(String titre, String nom, String prenom){
		super(titre,nom,prenom);
	}
	
	public String toString(){
		return String.format("Client : [%s]", super.toString());
	}
}
