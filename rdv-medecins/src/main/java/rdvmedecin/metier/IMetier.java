package rdvmedecin.metier;

import java.util.Date;
import java.util.List;

import rdvmedecin.domain.AgendaMedecinJour;
import rdvmedecin.entities.Client;
import rdvmedecin.entities.Creneau;
import rdvmedecin.entities.Medecin;
import rdvmedecin.entities.Rv;

public interface IMetier {
	//liste des clients
	public List<Client> getAllClients();
	//liste des Medecins
	public List<Medecin> getAllMedecins();
	//liste des creneaux horraires d'un medecin
	public List<Creneau> getAllCreneaux(long idMedecin);
	//liste des Rendez vous d'un medecin un jour donné
	public List<Rv> getRvMedecinJour(long idMedecin,Date jour);
	//trouver un client identifié par son id
	public Client getClientById(long id);
	//trouver un medecin identifié par son id
	public Medecin getMedecinById(long id);
	//trouver un rendez vous par son id
	public Rv getRvById(long id);
	//trouver un creneau horraire identifié par son id
	public Creneau getCreneauById(long id);
	//ajouter un RV
	public Rv ajouterRv(Date jour,Creneau creneau,Client client);
	//supprimer un Rv
	public void supprimeRv(long idrv);
	//metier
	public AgendaMedecinJour getAgendaMedecinJour(long idMedecin, Date jour);
}
