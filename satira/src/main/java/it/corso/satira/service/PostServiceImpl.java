package it.corso.satira.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.corso.satira.model.Admin;
import it.corso.satira.model.Post;
import it.corso.satira.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import jakarta.persistence.Query; 


@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentiService commentiService;

  @Override
  public List<Post> elencoPost() {
    return (List<Post>) postRepository.findAll(
    Sort.by(Sort.Direction.fromString("desc"), "id"));
  }

  @Override
  public Post datiPost(Integer id) {
    Optional<Post> postOptional = postRepository.findById(id);
    if (postOptional.isPresent()) {
      return postOptional.get();
    }
    return null;
  }

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public String eliminazionePost(Integer id) {
    try {
      System.out.println("Tentativo di eliminare post con ID: " + id);

      // NON fare nessun cast qui
      Query deletePost = entityManager.createQuery(
          "DELETE FROM Post p WHERE p.id = :postId");
      deletePost.setParameter("postId", id);
      int result = deletePost.executeUpdate();

      System.out.println("Risultato eliminazione (numero righe eliminate): " + result);

      if (result > 0) {
        return "Post eliminato con successo";
      } else {
        return "Post non trovato o non eliminato";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "Errore durante l'eliminazione: " + e.getMessage();
    }
  }

  @Override
  public void creazionePost(Post post, MultipartFile immaginePost, String titolo, String contenuto,
      LocalDateTime dataPubblicazione,
      Admin idAdmin, Integer visible) {

    if (idAdmin == null) {
      throw new IllegalArgumentException("L'admin non può essere nullo.");
    }

    post.setTitolo(titolo);
    post.setContenuto(contenuto);
    post.setDataPubblicazione(dataPubblicazione);
    post.setCommento(new ArrayList<>());
    post.setVisible(visible);
    post.setAdmin(idAdmin);

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

  @Override
  public Object[] validazionePost(Post post, String titolo, String contenuto, LocalDateTime dataPubblicazione,
      Integer visible, Admin idAdmin) {
    // post.setCommento((List<Commento>) commentiService.datiCommento(idCommento));
    post.setTitolo(titolo);
    Map<String, String> errori = new HashMap<>();
    if (!titolo.matches("[a-zA-Z0-9\\sàèìòù,.':-]{1,500}")) {
      errori.put("titolo", "Caratteri non ammessi in titolo");
    }

    if (!errori.isEmpty()) {
      return new Object[] { post, errori };
    }
    return null;

  }

}
