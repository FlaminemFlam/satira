package it.corso.satira.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.corso.satira.model.Post;

public interface PostService {
    List<Post> elencoPosts();

    Post datiPost(Integer id);

    String eliminazionePost(Integer id);

    void registrazionePost(Post post, MultipartFile immaginePost, String titolo, String contenuto, LocalDateTime dataPubblicazione,Integer idCommento, Integer idAdmin);
}
