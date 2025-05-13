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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="post")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "immagine_post")
    private String immaginePost;
    private String titolo;
    private String contenuto;

    @Column(name = "data_pubblicazione")
    private LocalDateTime dataPubblicazione;

    @ManyToOne(cascade = CascadeType.REFRESH) //refresh: quando registro un post NON registro admin
    @JoinColumn(name ="fk_id_admin", referencedColumnName = "id")
    private Admin admin;

    @OneToMany //ad un post corrispondono molti commenti
    (
        mappedBy = "post",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        orphanRemoval = true
    )
    private List <Commento> commento = new ArrayList<>();

    private Integer visible; 
}
