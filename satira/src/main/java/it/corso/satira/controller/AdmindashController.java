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
import it.corso.satira.service.AdminService;
import jakarta.servlet.http.HttpSession;

//localhost:8080/admidash
@Controller
@RequestMapping("/admindash")
public class AdmindashController {

    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;
    
    
    @GetMapping
    public String renderPage(HttpSession session, Model model, @RequestParam(required = false) String esito){
        if(session.getAttribute("admin") == null){
            return "redirect:/loginAdmin";
        }
        Admin adminSessione = (Admin) session.getAttribute("admin");
        Admin admin = adminService.datiAdmin(adminSessione.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("esito", esito);
        return "admindash";
    }

    
    @PostMapping("/create-admin")
    public String createAdmin(@RequestParam String username, @RequestParam String nickname, @RequestParam String password) {
        
        Admin admin = new Admin(); // `img` può essere NULL
        adminRepository.save(admin);
        
        return "redirect:/admindash?success";
    }

}


