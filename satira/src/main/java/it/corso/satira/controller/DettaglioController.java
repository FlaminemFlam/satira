package it.corso.satira.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.model.Post;
import it.corso.satira.service.PostService;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String renderPage(@RequestParam(defaultValue = "0") Integer id, Model model){
        Post post = postService.datiPost(id);
        if(post == null){
            return "redirect:/";
        }
        model.addAttribute("post",post);
        return "dettaglio";
    }
}