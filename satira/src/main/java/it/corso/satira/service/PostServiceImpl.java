package it.corso.satira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.satira.model.Post;
import it.corso.satira.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostRepository postRepository;

  @Override
  public List<Post> elencoPosts() {
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

}
