package it.corso.satira.service;

import java.util.List;

import it.corso.satira.model.Post;

public interface PostService {
    List<Post> elencoPosts();
    Post datiPost(Integer id);
    String eliminazionePost(Integer id);
}
