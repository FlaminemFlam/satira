package it.corso.satira.repository;

import org.springframework.data.repository.CrudRepository;

import it.corso.satira.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findByProfiloUsername(String username);
}
