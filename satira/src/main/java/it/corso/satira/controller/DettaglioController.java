package it.corso.satira.controller;

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
        Post post = postService.datiPost(id);
        Commento commento = commentiService.datiCommento(id);
        Admin admin = id == null ? new Admin() : adminService.datiAdmin(id);
        if (post == null) {
            return "redirect:/";
        }
        model.addAttribute("post", post);
        model.addAttribute("commento", commento);
        model.addAttribute("admin", admin);
        return "dettaglio";
    }
}