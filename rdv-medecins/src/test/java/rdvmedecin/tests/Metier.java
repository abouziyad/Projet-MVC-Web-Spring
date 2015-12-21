package rdvmedecin.tests;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rdvmedecin.config.DomainAndPersistenceConfig;
import rdvmedecin.domain.AgendaMedecinJour;
import rdvmedecin.entities.Client;
import rdvmedecin.entities.Creneau;
import rdvmedecin.entities.Medecin;
import rdvmedecin.entities.Rv;
import rdvmedecin.metier.IMetier;

@SpringApplicationConfiguration(classes = DomainAndPersistenceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Metier {

	@Autowired
	private IMetier metier;

	@Test
	public void test1() {
		// affichage clients
		List<Client> clients = metier.getAllClients();
		display("Liste des Clients", clients);
		// affichage des medecins
		
		List<Medecin> medecins = metier.getAllMedecins();
		display("Liste des medecins", medecins);
		
		// affichage créneaux d'un medecin
		Medecin medecin = medecins.get(1);
		List<Creneau> creneaux = metier.getAllCreneaux(medecin.getId());
		display(String.format("Liste des créneaux du medecin %s", medecin), creneaux);
		// liste des Rv d'un medecin, un jour donné
		Date jour = new Date();
		display(String.format("Liste des rv du medecin %s, le [%s]", medecin, jour),
				metier.getRvMedecinJour(medecin.getId(), jour));
		// ajouter un RV
		Rv rv = null;
		Creneau créneau = creneaux.get(2);
		Client client = clients.get(1);
		System.out.println(
				String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, créneau, client));
		rv = metier.ajouterRv(jour, créneau, client);
		// vérification
		Rv rv2 = metier.getRvById(rv.getId());
		Assert.assertEquals(rv, rv2);
		display(String.format("Liste des Rv du medecin %s, le [%s]", medecin, jour),
				metier.getRvMedecinJour(medecin.getId(), jour));
		// ajouter un RV dans le même créneau du même jour
		// doit provoquer une exception
		System.out.println(
				String.format("Ajout d'un Rv le [%s] dans le créneau %s pour le client %s", jour, créneau, client));
		Boolean erreur = false;
		try {
			rv = metier.ajouterRv(jour, créneau, client);
			System.out.println("Rv ajouté");
		} catch (Exception ex) {
			Throwable th = ex;
			while (th != null) {
				System.out.println(ex.getMessage());
				th = th.getCause();
			}
			// on note l'erreur
			erreur = true;
		}
		// on vérifie qu'il y a eu une erreur
		Assert.assertTrue(erreur);
		// liste des RV
		display(String.format("Liste des Rv du medecin %s, le [%s]", medecin, jour),
				metier.getRvMedecinJour(medecin.getId(), jour));
		// affichage agenda
		AgendaMedecinJour agenda = metier.getAgendaMedecinJour(medecin.getId(), jour);
		System.out.println(agenda);
		Assert.assertEquals(rv, agenda.getCreneauMedecinJour()[2].getRv());
		// supprimer un RV
		System.out.println("Suppression du Rv ajouté");
		metier.supprimeRv(rv.getId());
		// vérification
		rv2 = metier.getRvById(rv.getId());
		Assert.assertNull(rv2);
		display(String.format("Liste des Rv du medecin %s, le [%s]", medecin, jour),
				metier.getRvMedecinJour(medecin.getId(), jour));
	}

	// méthode utilitaire - affiche les éléments d'une collection
	private void display(String message, Iterable<?> elements) {
		System.out.println(message);
		for (Object element : elements) {
			System.out.println(element);
		}
	}
}
