package it.corso.satira.controller;

import java.util.UUID;

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

    //Creo un token univoco per l'invio mail grazie alla classe UUID di java.util
    String token = UUID.randomUUID().toString(); 



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
        // Costruisci il contenuto dell'email, sostituendo i placeholder con i valori del form
        String confirmationLink = "http://localhost:8080/admindash?token=" + token;
        String emailBody = "Salve mi chiamo " + form.getCognome() + " " + form.getNome() + ",\n\n" +
        "Richiedo di avere accesso come admin, clicca sul seguente link:\n" +
        confirmationLink + "\n\n" +
        "Cordiali Saluti, " + form.getNome() + " " + form.getCognome() + "\n" ;
        emailService.sendMail("gia.minisi@gmail.com", "Conferma Admin", emailBody);
        
        return "redirect:/adminForm?success";
    }
}
