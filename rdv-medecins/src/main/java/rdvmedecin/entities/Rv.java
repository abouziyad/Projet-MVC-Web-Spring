package rdvmedecin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="rv")
public class Rv extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date jour;
	
	//un Rv est lié a un client
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_client")
	private Client client;
	
	//un rv est lié a un creneau
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_creneau")
	private Creneau creneau;
	
	//clé etrangere
	@Column(name="id_client",insertable=false,updatable=false)
	private long id_Client;
	@Column(name="id_creneau",insertable=false,updatable=false)
	private long id_Creneau;
	
	public Rv(){
		
	}
	
	public Rv(Date jour,Client client,Creneau creneau){
		this.jour=jour;
		this.client=client;
		this.creneau=creneau;
	}
	
	public String toString(){
		return String.format("Rv [%d, %s, %d, %d]", id,jour,client.id,creneau.id);
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	//clé etrangere
	public long getId_Client() {
		return id_Client;
	}

	public void setId_Client(long id_Client) {
		this.id_Client = id_Client;
	}

	public long getId_Creneau() {
		return id_Creneau;
	}

	public void setId_Creneau(long id_Creneau) {
		this.id_Creneau = id_Creneau;
	}
}
