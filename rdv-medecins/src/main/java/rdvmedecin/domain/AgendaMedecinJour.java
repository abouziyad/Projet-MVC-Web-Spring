package rdvmedecin.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import rdvmedecin.entities.Medecin;

public class AgendaMedecinJour implements Serializable {
	private static final long serialVersionUID = 1L;

	private Medecin medecin;
	private Date jour;
	private CreneauMedecinJour[] creneauMedecinJour;

	public AgendaMedecinJour() {
	}

	public AgendaMedecinJour(Medecin medecin, Date jour, CreneauMedecinJour[] creneauMedecinJour) {
		this.medecin = medecin;
		this.jour = jour;
		this.creneauMedecinJour = creneauMedecinJour;
	}

	public String toString() {
		StringBuffer str = new StringBuffer("");
		for (CreneauMedecinJour cr : creneauMedecinJour) {
			str.append(" ");
			str.append(cr.toString());
		}
		return String.format("Agenda[%s,%s,%s]", medecin, new SimpleDateFormat("dd/MM:yyyy").format(jour),
				str.toString());
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public CreneauMedecinJour[] getCreneauMedecinJour() {
		return creneauMedecinJour;
	}

	public void setCreneauMedecinJour(CreneauMedecinJour[] creneauMedecinJour) {
		this.creneauMedecinJour = creneauMedecinJour;
	}
}
