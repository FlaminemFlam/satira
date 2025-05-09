package it.corso.satira.repository;

import org.springframework.data.repository.CrudRepository;

import it.corso.satira.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

}
