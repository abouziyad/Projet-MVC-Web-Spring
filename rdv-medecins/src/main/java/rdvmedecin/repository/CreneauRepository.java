package rdvmedecin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import rdvmedecin.entities.Creneau;

public interface CreneauRepository extends CrudRepository<Creneau, Long> {
	@Query("select c from Creneau c where c.medecin.id=?1")
	Iterable <Creneau> getAllCreneaux(long idMedecin);

}
