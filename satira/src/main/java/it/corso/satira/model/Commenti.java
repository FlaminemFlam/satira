package it.corso.satira.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="commenti")
public class Commenti {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String testo;

  @Column(name = "immagine_commento")
  private String immagineCommento;

  @Column(name = "data_commento")
  private LocalDateTime dataCommento;

      @OneToMany //ad un post corrispondono molti commenti
    (
        mappedBy = "commenti",
        cascade = CascadeType.REMOVE,
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private List<Post> posts = new ArrayList<>();
}
