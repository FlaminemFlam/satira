package it.corso.satira.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.model.Post;
import it.corso.satira.model.Commento;
import it.corso.satira.model.Admin;
import it.corso.satira.service.PostService;
import it.corso.satira.service.CommentiService;
import it.corso.satira.service.AdminService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentiService commentiService;

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String renderPage(Model model, @RequestParam(required = false) Integer id){
        List<Post> posts = postService.elencoPost();
        List<Commento> commenti = commentiService.elencoCommenti();
        Admin admin = id == null? new Admin() : adminService.datiAdmin(id);
        model.addAttribute("posts", posts);
        model.addAttribute("commenti",commenti);
        model.addAttribute("admin",admin);
        return "index";
    }
}