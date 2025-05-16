package it.corso.satira.controller;


import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import it.corso.satira.model.Commento;
import it.corso.satira.model.Post;
import it.corso.satira.service.CommentiService;
import it.corso.satira.service.PostService;

@Controller
public class CommentoController {

    @Autowired
    private CommentiService commentiService;

    @Autowired
    private PostService postService;

    @PostMapping("/aggiungicommento")
    public String aggiungiCommento(@RequestParam(required = false) String testo, @RequestParam("postId") Integer postId,@RequestParam(value = "immagine", required = false) String immagine) {

        // Crea un nuovo commento
        Commento commento = new Commento();
        commento.setTesto(testo);
        commento.setDataCommento(LocalDateTime.now());

        // Gestione dell'immagine se presente
        if (immagine != null && !immagine.isEmpty()) {
            
        // Salva l'immagine nel campo immagineCommento
        commento.setImmagineCommento(immagine);

    }
        // Imposta il post a cui si riferisce il commento
        Post post = postService.datiPost(postId);
        commento.setPost(post);

        // Salva il commento
        commentiService.registraCommento(commento);

        // Reindirizza alla pagina di dettaglio
        return "redirect:/dettaglio?id=" + postId;
}
}