package it.corso.satira.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.satira.model.Post;
import it.corso.satira.service.PostService;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String renderPage(Model model){
        List<Post> posts = postService.elencoPosts();
        model.addAttribute("posts", posts);
        return "index";
    }
}