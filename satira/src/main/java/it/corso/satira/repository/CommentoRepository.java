package it.corso.satira.repository;

import org.springframework.data.repository.CrudRepository;

import it.corso.satira.model.Commento;

public interface CommentoRepository extends CrudRepository<Commento, Integer> {

}
