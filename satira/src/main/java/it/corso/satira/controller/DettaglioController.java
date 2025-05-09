package it.corso.satira.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @GetMapping
    public String renderPage(){
        return "dettaglio";
    }
}