package it.corso.satira.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.corso.satira.model.Admin;
import it.corso.satira.model.BecomeAdmin;
import it.corso.satira.model.Post;
import it.corso.satira.service.AdminService;
import it.corso.satira.service.EmailService;
import it.corso.satira.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Base64;

//localhost:8080/admidash
@Controller
@RequestMapping("/admindash")
public class AdmindashController {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private EmailService emailService;
    
    private Post post;
    
    private Map<String, String> errori;
    
    @GetMapping
    public String renderPage(HttpSession session, Model model, @RequestParam(required = false) Integer id,
    @RequestParam(required = false) String esito) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/loginAdmin";
        }
        
        if (errori == null)
        post = id == null ? new Post() : postService.datiPost(id);
        
        List<Post> posts = postService.elencoPost();
        Admin adminSessione = (Admin) session.getAttribute("admin");
        Admin admin = adminService.datiAdmin(adminSessione.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("esito", esito);
        model.addAttribute("posts", posts);
        model.addAttribute("post", post);
        model.addAttribute("errori", errori);
        return "admindash";
    }
    
    @SuppressWarnings("unchecked")
    @PostMapping
    public String gestioneForm(HttpSession session, @RequestParam String titolo, @RequestParam String contenuto,
    @RequestParam LocalDateTime dataPubblicazione, @RequestParam(required = false) Integer idCommento,
    @RequestParam(required = false) MultipartFile immaginePost, @RequestParam Integer visible) {
        
        // Recupero l'oggetto Admin dalla sessione
        Admin adminSession = (Admin) session.getAttribute("admin");
        if (adminSession == null) {
            return "redirect:/loginAdmin";
        }
        
        // Validazione dati del post (assicurati che validazionePost accetti un oggetto
        // Admin se necessario)
        Object[] esitoValidazione = postService.validazionePost(post, titolo, contenuto, dataPubblicazione, visible,
        adminSession);
        if (esitoValidazione != null) {
            post = (Post) esitoValidazione[0];
            errori = (Map<String, String>) esitoValidazione[1];
            return "redirect:/admindash";
        }
        
        // Creazione del nuovo post: passo direttamente l'oggetto Admin della sessione
        postService.creazionePost(post, immaginePost, titolo, contenuto, dataPubblicazione, adminSession, visible);
        
        post = null;
        errori = null;
        return "redirect:/admindash";
    }
    
    
    // Creazione Admin Render
    @GetMapping("/creaAdmin")
    public String renderPagina(HttpSession session, Model model,
    @RequestParam(required = false) String esito,
    @RequestParam(required = false) String token) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/loginAdmin";
        }
        
        // Inizializzazione BecomeAdmin, Serve per il Binding del form (Associazione automatica)
        BecomeAdmin becomeAdmin = new BecomeAdmin();
        
        // Se il token è presente ed è valido, decodificalo e popola l'oggetto BecomeAdmin
        if (token != null && !token.isEmpty()) {
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(token);
                String decodedString = new String(decodedBytes);
                
                // La stringa è strutturata come "nome%cognome%email"
                
                String[] parts = decodedString.split("%");
                if (parts.length == 3) {
                    becomeAdmin.setNome(parts[0]);
                    becomeAdmin.setCognome(parts[1]);
                    becomeAdmin.setEmail(parts[2]);
                } else {
                    model.addAttribute("error", "Token malformato: numero di componenti non valido.");
                }
            } catch (IllegalArgumentException ex) {
                model.addAttribute("error", "Errore nella decodifica del token.");
            }
        }
        
        // Salvataggio dell'oggetto nella sessione per recuperarlo nel POST
        session.setAttribute("becomeAdmin", becomeAdmin);
        
        // Recupero l'admin corrente dalla sessione
        Admin adminSessione = (Admin) session.getAttribute("admin");
        Admin admin = adminService.datiAdmin(adminSessione.getId());
        
        model.addAttribute("admin", admin);
        model.addAttribute("esito", esito);
        model.addAttribute("becomeAdmin", becomeAdmin);
        
        return "creaAdmin";
    }
    
    
    
    
    @PostMapping("/creaAdmin")
    public String createAdmin(HttpSession session,
    @Valid @ModelAttribute Admin admin,
    BindingResult result,
    Model model) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/loginAdmin";
        }
        if (result.hasErrors()) {
            return "creaAdmin";
        }
        
        String controlloAdmin = adminService.controlloUsername(admin.getUsername());
        if (controlloAdmin != null) {
            model.addAttribute("controlloAdmin", controlloAdmin);
            return "creaAdmin";
        }
        
        // Registrazione dell'admin nel DB
        adminService.registrazioneAdmin(admin);
        
        // Recupera BecomeAdmin salvato nella sessione
        BecomeAdmin form = (BecomeAdmin) session.getAttribute("becomeAdmin");
        if (form == null || form.getEmail() == null) {
            model.addAttribute("error", "Dati di registrazione non trovati, riprova o contatta l'assistenza.");
            return "creaAdmin";
        }
        
        // Costruzione del messaggio per inviare le credenziali
        String subject = "Credenziali per accesso Admin";
        String body = "Gentile " + form.getNome() + " " + form.getCognome() + ",\n\n" +
        "La registrazione è andata a buon fine. Le sue credenziali per accedere al sistema sono:\n" +
        "Username: " + admin.getUsername() + "\n" +
        "Password: " + admin.getPassword() + "\n\n" +
        "Per maggiore sicurezza, si consiglia di cambiare la password al primo accesso.\n\n" +
        "Cordiali saluti,\n" +
        "Team di Amministrazione";
        
        // Invio dell'email con le credenziali
        emailService.sendMail(form.getEmail(), subject, body);
        
        // Rimozione dei dati dalla sessione (se non servono più)
        session.removeAttribute("becomeAdmin");
        
        return "redirect:/admindash/creaAdmin?success";
    }
    
    
    // Logout Admin
    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        session.removeAttribute(("admin"));
        return "redirect:/";
    }
    
    
    // Eliminazione Post
    @GetMapping("/elimina")
    public String rimozionePost(@RequestParam Integer id) {
        String esito = postService.eliminazionePost(id);
        if (esito != null) {
            return "redirect:/admindash?esito=" + esito;
        }
        return "redirect:/admindash";
    }
}