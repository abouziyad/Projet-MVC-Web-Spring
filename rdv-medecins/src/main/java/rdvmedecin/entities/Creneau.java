package rdvmedecin.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="creneaux")
public class Creneau extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	private int hdebut;
	private int hfin;
	private int mdebut;
	private int mfin;
	
	//Un creneau est associé a un medecin
	//fetch=FetchType.LAZY indique que lorsqu'on demande un creneau au context de persistence 
	//seule l'entité creneau est cherché 
	//sans Medecin cela economise la memoire et donc les performance de l'applicaion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_medecin")
	private Medecin medecin;
	
	//indique le nom de la colonne : clé etrangere
	//la clé etrangere id_medecin a deja ete utilisé precedement ce qui est interdit 
	//dans la norme Jpa d'ou les attribus insertable=false,updatable=false ici la colonne
	//ne peut etre que lu
	@Column(name="id_medecin",insertable=false,updatable=false)
	private long idMedecin;

	public Creneau() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Creneau(Medecin medecin, int hdebut, int mdebut, int hfin, int mfin) {
		super();
		this.medecin = medecin;
		this.hdebut = hdebut;
		this.mdebut = mdebut;
		this.hfin = hfin;
		this.mfin = mfin;
	}
	
	public String toString(){
		return String.format("Crénaux [%d, %d, %d, %d:%d %d:%d]", id,version,idMedecin,hdebut,mdebut,hfin,mfin);		
	}
	
	//clé etrangere
	public long getIdMedecin(){
		return idMedecin;
	}

	//getters and Setters
	public int getHdebut() {
		return hdebut;
	}

	public void setHdebut(int hdebut) {
		this.hdebut = hdebut;
	}

	public int getHfin() {
		return hfin;
	}

	public void setHfin(int hfin) {
		this.hfin = hfin;
	}

	public int getMdebut() {
		return mdebut;
	}

	public void setMdebut(int mdebut) {
		this.mdebut = mdebut;
	}

	public int getMfin() {
		return mfin;
	}

	public void setMfin(int mfin) {
		this.mfin = mfin;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	
	
}
