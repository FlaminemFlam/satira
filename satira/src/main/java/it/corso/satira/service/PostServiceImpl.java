package it.corso.satira.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.corso.satira.model.Commento;
import it.corso.satira.model.Post;
import it.corso.satira.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentiService commentiService;

  @Autowired
  private AdminService adminService;

  @Override
  public List<Post> elencoPost() {
    return (List<Post>) postRepository.findAll();
  }

  @Override
  public Post datiPost(Integer id) {
    Optional<Post> postOptional = postRepository.findById(id);
    if (postOptional.isPresent()) {
      return postOptional.get();
    }
    return null;
  }

  @Override
  public String eliminazionePost(Integer id) {
    postRepository.deleteById(id);
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void creazionePost(Post post, MultipartFile immaginePost, String titolo, String contenuto,
      LocalDateTime dataPubblicazione, Integer idCommento, Integer idAdmin) {

    post.setTitolo(titolo);
    post.setContenuto(contenuto);
    post.setDataPubblicazione(dataPubblicazione);
    post.setCommento((List<Commento>) commentiService.datiCommento(idCommento));
    post.setAdmin(adminService.datiAdmin(idAdmin));

    if (immaginePost != null && !immaginePost.isEmpty()) {
      try {
        String formato = immaginePost.getContentType();
        String immaginePostCodificata = "data:" + formato + ";base64,"
            + Base64.getEncoder().encodeToString(immaginePost.getBytes());
        post.setImmaginePost(immaginePostCodificata);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    postRepository.save(post);
  }

}
