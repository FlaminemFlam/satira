package it.corso.satira.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.corso.satira.model.Admin;
import it.corso.satira.model.Post;

public interface PostService {
        List<Post> elencoPost();
        
        Post datiPost(Integer id);
        
        String eliminazionePost(Integer id);
        
        void creazionePost(Post post, MultipartFile immaginePost, String titolo, String contenuto,
        LocalDateTime dataPubblicazione, Admin idAdmin, Integer visible);
        
        Object[] validazionePost(Post post, String titolo, String contenuto, LocalDateTime dataPubblicazione, Integer visible, Admin idAdmin);
}
