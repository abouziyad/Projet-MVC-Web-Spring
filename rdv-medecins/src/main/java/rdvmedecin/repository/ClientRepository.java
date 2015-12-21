package rdvmedecin.repository;

import org.springframework.data.repository.CrudRepository;

import rdvmedecin.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
