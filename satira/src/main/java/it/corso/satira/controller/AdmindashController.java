package it.corso.satira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admindash")
public class AdmindashController {

    @GetMapping
    public String renderPage(){
        return "admindash";
    }
}
