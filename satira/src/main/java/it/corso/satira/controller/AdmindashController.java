package it.corso.satira.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.model.Admin;
import it.corso.satira.service.AdminService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//localhost:8080/admidash
@Controller
@RequestMapping("/admindash")
public class AdmindashController {

    

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
    public String createAdmin(@Valid @ModelAttribute Admin admin, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "admindash";
        }
        String controlloAdmin = adminService.controlloUsername(admin.getUsername());
        if(controlloAdmin != null){
            model.addAttribute("controlloAdmin", controlloAdmin);
            return "admindash";
        }
        adminService.registrazioneAdmin(admin);
        return "redirect:/admindash?success";
    }


}


