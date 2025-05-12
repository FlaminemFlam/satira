package it.corso.satira.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.satira.model.Admin;
import it.corso.satira.model.BecomeAdmin;
import it.corso.satira.repository.AdminRepository;
import it.corso.satira.service.EmailService;

@Controller
@RequestMapping("/admindash")
public class AdmindashController {
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private AdminRepository adminRepository;
    
    
    
    String token = UUID.randomUUID().toString(); //Creo un token univoco per l'invio mail grazie alla classe UUID di java.util
    
    @GetMapping
    public String renderPage(){
        return "admindash";
    }
    
    
    // Mostra il form precompilato
    @GetMapping("/admin-request")
    public String showAdminRequestForm(Model model) {
        BecomeAdmin form = new BecomeAdmin();
        // Imposta dei valori di default (se necessario)
        form.setUsername("Nome di Default");       
        form.setEmail("email@example.com");
        model.addAttribute("BecomeAdmin", form);
        return "adminForm"; // nome del template Thymeleaf (es. admin-form.html)
    }
    
    // Gestisce l'invio del form
    @PostMapping("/admin-request")
    public String processAdminRequest(@ModelAttribute("BecomeAdmin") BecomeAdmin form) {
        // Costruisci il contenuto dell'email, sostituendo i placeholder con i valori del form
        String confirmationLink = "http://localhost:8080/admindash?token=" + token;
        String emailBody = "Ciao " + form.getUsername() + ",\n\n" +
        "Per confermare il tuo accesso come admin, clicca sul seguente link:\n" +
        confirmationLink + "\n\n" +
        "Cordiali Saluti,\nIl Team";
        emailService.sendMail(form.getEmail(), "Conferma Admin", emailBody);
        
        return "redirect:/adminForm?success";
    }
    
    @PostMapping("/create-admin")
    public String createAdmin(@RequestParam String username, 
    @RequestParam String nickname, 
    @RequestParam String password) {
        
        Admin admin = new Admin(); // `img` può essere NULL
        adminRepository.save(admin);
        
        return "redirect:/admindash?success";
    }
    
    // @GetMapping("/confirm-admin")
    // public String confirmAdmin(@RequestParam String token, Model model) {
    //     BecomeAdmin request = emailService.findByToken(token);
        
    //     if (request != null && !request.isConfirmed()) {
    //         // Genera username, nickname e password
    //         String username = generateUsername(request.getUsername());
    //         String nickname = request.getUsername();
    //         String password = generatePassword();
            
    //         // Salva l'utente nel database
    //         Admin admin = new Admin();
    //         adminRepository.save(admin);
            
    //         // Aggiorna la richiesta come confermata
    //         emailService.confirmRequest(request);
            
    //         // Mostra le credenziali all'utente
    //         model.addAttribute("username", username);
    //         model.addAttribute("password", password);
    //         return "admin-confirmed";
    //     } else {
    //         return "error-page"; // Token non valido o già usato
    //     }
    // }
    
    
    
}


