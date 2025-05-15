package it.corso.satira.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String aggiungiCommento(
            @RequestParam("testo") String testo,
            @RequestParam("postId") Integer postId,
            RedirectAttributes redirectAttributes) {
        
        // Crea un nuovo commento
        Commento commento = new Commento();
        commento.setTesto(testo);
        commento.setDataCommento(LocalDateTime.now());
        
        // Imposta il post a cui si riferisce il commento
        Post post = postService.datiPost(postId);
        commento.setPost(post);
        
        // Salva il commento
        commentiService.registraCommento(commento);
        
        // Reindirizza alla pagina di dettaglio
        return "redirect:/dettaglio?id=" + postId;
    }
}
