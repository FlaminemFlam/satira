package it.corso.satira.model;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="commenti")
public class Commento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String testo;

  @Column(name = "immagine_commento")
  private String immagineCommento;

  @Column(name = "data_commento")
  private LocalDateTime dataCommento;

  @ManyToOne (cascade = CascadeType.REFRESH)
  @JoinColumn (name="fk_id_post", referencedColumnName = "id")
  private Post post;

}

