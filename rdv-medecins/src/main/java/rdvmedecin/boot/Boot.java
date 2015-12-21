package rdvmedecin.boot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import rdvmedecin.config.DomainAndPersistenceConfig;
import rdvmedecin.entities.Client;
import rdvmedecin.entities.Creneau;
import rdvmedecin.entities.Rv;
import rdvmedecin.metier.IMetier;

public class Boot{

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
		app.setLogStartupInfo(false);
		ConfigurableApplicationContext context = app.run(args);
		
		IMetier metier = context.getBean(IMetier.class);
		try{
			Date jour = new Date();
			System.out.println(String.format("Ajout d'un Rv le [%s] dans le créneau 1 pour le client 1",
				    new SimpleDateFormat("dd/MM/yyyy").format(jour)));
			Client client = (Client) new Client().build(1L,1L);
			Creneau creneau = (Creneau) new Creneau().build(1L,1L);
			Rv rv = metier.ajouterRv(jour, creneau, client);
			System.out.println(String.format("Rv ajouté : %s", rv));
			
			//verification
			System.out.println("verif");
			creneau = metier.getCreneauById(1L);
			long idMedecin = creneau.getIdMedecin();
			display("Liste des rendez-vous", metier.getRvMedecinJour(idMedecin, jour));
			
			
		}catch(Exception e){
			System.out.println("Exception :"+e.getCause());
		}
		context.close();
	}
	private static <T> void display(String message, Iterable<T> elements) {
		System.out.println(message);
		for (T element : elements) {
			System.out.println(element);
		}
	}
}
