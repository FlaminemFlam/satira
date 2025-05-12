package it.corso.satira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.service.AdminService;
import jakarta.servlet.http.HttpSession;

//localhost:8080/loginAdmin
@Controller
@RequestMapping("/loginAdmin")
public class LoginAdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String renderPagina(@RequestParam(required = false) String errore, Model model, HttpSession session){
        if(session.getAttribute("admin") != null){
            return "redirect:/admindash";
        }
        model.addAttribute("errore", errore); // null oppure Credenziali errate
        return "loginAdmin";
    }

    @PostMapping
    public String gestioneForm(@RequestParam String username, @RequestParam String password, HttpSession session){
        String esitoControllo = adminService.controlloLogin(username, password, session);
        if(esitoControllo.equals("Credenziali Errate")){
            return "redirect:/loginAdmin?errore=" + esitoControllo;
        }
        return "redirect:/admindash";
    }
}
