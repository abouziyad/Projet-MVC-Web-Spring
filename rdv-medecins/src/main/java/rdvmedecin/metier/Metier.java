package rdvmedecin.metier;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import rdvmedecin.domain.AgendaMedecinJour;
import rdvmedecin.domain.CreneauMedecinJour;
import rdvmedecin.entities.Client;
import rdvmedecin.entities.Creneau;
import rdvmedecin.entities.Medecin;
import rdvmedecin.entities.Rv;
import rdvmedecin.repository.ClientRepository;
import rdvmedecin.repository.CreneauRepository;
import rdvmedecin.repository.MedecinRepository;
import rdvmedecin.repository.RvRepository;

@Service("metier")
public class Metier implements IMetier {

	//Repositories
	@Autowired
	MedecinRepository medecinRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CreneauRepository creneauRepository;
	@Autowired
	RvRepository rvRepository;
	
	@Override
	public List<Client> getAllClients() {
		return Lists.newArrayList(clientRepository.findAll());
	}

	@Override
	public List<Medecin> getAllMedecins() {
		return Lists.newArrayList(medecinRepository.findAll());
	}

	@Override
	public List<Creneau> getAllCreneaux(long idMedecin) {
		return Lists.newArrayList(creneauRepository.getAllCreneaux(idMedecin));
	}

	@Override
	public List<Rv> getRvMedecinJour(long idMedecin, Date jour) {
		return Lists.newArrayList(rvRepository.getRvMedecinJour(idMedecin, jour));
	}

	@Override
	public Client getClientById(long id) {
		return clientRepository.findOne(id);
	}

	@Override
	public Medecin getMedecinById(long id) {
		return medecinRepository.findOne(id);
	}

	@Override
	public Rv getRvById(long id) {
		return rvRepository.findOne(id);
	}

	@Override
	public Creneau getCreneauById(long id) {
		return creneauRepository.findOne(id);
	}

	@Override
	public Rv ajouterRv(Date jour, Creneau creneau, Client client) {
		return rvRepository.save(new Rv(jour,client,creneau));
	}

	@Override
	public void supprimeRv(long idrv) {
		rvRepository.delete(idrv);
	}

	@Override
	public AgendaMedecinJour getAgendaMedecinJour(long idMedecin, Date jour) {
		//liste des creneaux d'un medecin
		List<Creneau> creneauxHoraires = getAllCreneaux(idMedecin);
		//liste des Rendez vous de ce medecin le jour meme
		List<Rv> reservations = getRvMedecinJour( idMedecin, jour);
		//On creer un dictionnaire à partir des Rv pris
		Map<Long,Rv> hReservations = new Hashtable<Long,Rv>();
		for(Rv resa : reservations){
			hReservations.put(resa.getCreneau().getId(), resa);
		}
		
		//creation de l'agenda pour le jour demandé
		AgendaMedecinJour agenda = new AgendaMedecinJour();
		agenda.setMedecin(getMedecinById(idMedecin));
		agenda.setJour(jour);
		//les creneaux de reservation
		CreneauMedecinJour[] creneauxMedecinJour = new CreneauMedecinJour[creneauxHoraires.size()];
		agenda.setCreneauMedecinJour(creneauxMedecinJour);
		//remplissage des creneaux de reservation
		for(int i=0; i < creneauxHoraires.size();i++){
			//une ligne de l'agenda
			creneauxMedecinJour[i]=new CreneauMedecinJour();
			Creneau creneau = creneauxHoraires.get(i);
			long idCreneau = creneau.getId();
			creneauxMedecinJour[i].setCreneau(creneau);
			//verification dispo creneau
			if(hReservations.containsKey(idCreneau)){
				//le creneau est occupé-on note la reservation
				Rv resa = hReservations.get(idCreneau);
				creneauxMedecinJour[i].setRv(resa);
			}
		}
		return agenda;
	}

}
