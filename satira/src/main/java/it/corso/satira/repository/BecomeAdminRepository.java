package it.corso.satira.repository;

import org.springframework.data.repository.CrudRepository;

import it.corso.satira.model.BecomeAdmin;



public interface BecomeAdminRepository extends CrudRepository<BecomeAdmin,Integer> {
    BecomeAdmin findByToken(String token);
}
