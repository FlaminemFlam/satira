package it.corso.satira.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.model.Admin;
import it.corso.satira.repository.AdminRepository;

//localhost:8080/admidash
@Controller
@RequestMapping("/admindash")
public class AdmindashController {

    
    @Autowired
    private AdminRepository adminRepository;
    
    
    @GetMapping
    public String renderPage(Model model){
        model.addAttribute("admin", new Admin());
        return "admindash";
    }

    
    @PostMapping("/create-admin")
    public String createAdmin(@RequestParam String username, @RequestParam String nickname, @RequestParam String password) {
        
        Admin admin = new Admin(); // `img` può essere NULL
        adminRepository.save(admin);
        
        return "redirect:/admindash?success";
    }

}


