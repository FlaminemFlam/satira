package it.corso.satira.controller;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.satira.model.BecomeAdmin;
import it.corso.satira.service.EmailService;

@Controller
@RequestMapping("/adminForm")
public class FormAdminController {
    
    @Autowired
    private EmailService emailService;
    
    
    // Mostra il form precompilato
    @GetMapping
    public String showAdminRequestForm(Model model) {
        BecomeAdmin form = new BecomeAdmin();
        // Imposta dei valori di default (se necessario)
        model.addAttribute("BecomeAdmin", form);
        return "adminForm"; 
    }
    
    // Gestisce l'invio del form
    @PostMapping
    public String processAdminRequest(@ModelAttribute("BecomeAdmin") BecomeAdmin form) {
        // Uso percentuale (%) per splittare i dati da inviare
        String dati = form.getNome() + "%" + form.getCognome() + "%" + form.getEmail();

        // Codifico i dati in Base64
        String token = Base64.getEncoder().encodeToString(dati.getBytes()); 
        
        // Costruisco il contenuto dell'email
        String confirmationLink = "http://localhost:8080/admindash/creaAdmin?token=" + token;
        String emailBody = "Salve mi chiamo " + form.getCognome() + " " + form.getNome() + ",\n\n" +
        "Richiedo di avere accesso come Admin, clicca sul seguente link:\n" +
        confirmationLink + "\n\n" +
        "Cordiali Saluti, " + form.getNome() + " " + form.getCognome() + "\n" ;
        emailService.sendMail("gia.minisi@gmail.com", "Crea Admin", emailBody);
        
        return "redirect:/adminForm?success";
    }
}
