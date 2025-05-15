package it.corso.satira.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
            @RequestParam(value = "immagine", required = false) MultipartFile immagine,
            RedirectAttributes redirectAttributes) {

        // Crea un nuovo commento
        Commento commento = new Commento();
        commento.setTesto(testo);
        commento.setDataCommento(LocalDateTime.now());

        // Gestione dell'immagine se presente
        if (immagine != null && !immagine.isEmpty()) {
            try {
                // Converte l'immagine in una stringa Base64
                String base64Image = Base64.getEncoder().encodeToString(immagine.getBytes());
                String imageFormat = immagine.getContentType();
                String dataUri = "data:" + imageFormat + ";base64," + base64Image;

                // Salva l'immagine nel campo immagineCommento
                commento.setImmagineCommento(dataUri);
            } catch (IOException e) {
                e.printStackTrace();
                // Gestire l'errore in qualche modo, ad esempio con un messaggio flash
                redirectAttributes.addFlashAttribute("errorMessage", "Errore nel caricamento dell'immagine");
                return "redirect:/dettaglio?id=" + postId;
            }
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