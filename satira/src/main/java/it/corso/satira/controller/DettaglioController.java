package it.corso.satira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.model.Admin;
import it.corso.satira.model.Commento;
import it.corso.satira.model.Post;
import it.corso.satira.service.AdminService;
import it.corso.satira.service.CommentiService;
import it.corso.satira.service.PostService;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentiService commentiService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String renderPage(@RequestParam(defaultValue = "0") Integer id, Model model) {
        // Prima recupera il post
        Post post = postService.datiPost(id);

        // Verifica se il post è nullo prima di procedere
        if (post == null) {
            return "redirect:/";
        }

        // Ora recupera l'admin usando l'ID dell'admin associato al post
        Admin admin = null;
        if (post.getAdmin() != null) {
            admin = adminService.datiAdmin(post.getAdmin().getId());
        } else {
            admin = new Admin(); // Admin vuoto se il post non ha un admin associato
        }

        // Recupera i commenti
        List<Commento> commenti = commentiService.elencoCommenti();
        List<Post> posts = postService.elencoPost();

        // Aggiungi gli attributi al modello
        model.addAttribute("post", post);
        model.addAttribute("commenti", commenti);
        model.addAttribute("admin", admin);
        model.addAttribute("posts", posts);
        model.addAttribute("nuovoCommento", new Commento());
        
        return "dettaglio";
    }
}