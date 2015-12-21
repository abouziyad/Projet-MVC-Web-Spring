package rdvmedecin.repository;

import org.springframework.data.repository.CrudRepository;

import rdvmedecin.entities.Medecin;

public interface MedecinRepository extends CrudRepository<Medecin, Long> {

}
