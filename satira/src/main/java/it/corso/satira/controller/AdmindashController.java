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
import it.corso.satira.model.Post;
import it.corso.satira.service.AdminService;
import it.corso.satira.service.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//localhost:8080/admidash
@Controller
@RequestMapping("/admindash")
public class AdmindashController {

    @Autowired
    private PostService postService;
    @Autowired
    private AdminService adminService;

    private Post post;

    private Map<String, String> errori;

    @GetMapping
    public String renderPage(HttpSession session, Model model, @RequestParam(required = false) Integer id, @RequestParam(required = false) String esito) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/loginAdmin";
        }

        if(errori == null)
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
    public String gestioneForm(@RequestParam String titolo, @RequestParam String contenuto, @RequestParam LocalDateTime dataPubblicazione, @RequestParam Integer idAdmin, @RequestParam Integer idCommento, @RequestParam(required = false) MultipartFile immaginePost, @RequestParam Integer visible) {
        Object[] esitoValidazione = postService.validazionePost(post, titolo, contenuto, dataPubblicazione, idAdmin,
                visible, idCommento);
        if (esitoValidazione != null) {
            post = (Post) esitoValidazione[0];
            errori = (Map<String, String>) esitoValidazione[1];
            return "redirect:/admindash";
        }

        postService.creazionePost(post, immaginePost, titolo, contenuto, dataPubblicazione, idCommento, idAdmin);
        post = null;
        errori = null;
        return "redirect:/admindash";
    }

    @GetMapping("/creaAdmin")
    public String renderPagina(HttpSession session, Model model, @RequestParam(required = false) String esito) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/loginAdmin";
        }
        Admin adminSessione = (Admin) session.getAttribute("admin");
        Admin admin = adminService.datiAdmin(adminSessione.getId());
        model.addAttribute("admin", admin);
        model.addAttribute("esito", esito);
        return "creaAdmin";
    }

    @PostMapping("/creaAdmin")
    public String createAdmin(HttpSession session, @Valid @ModelAttribute Admin admin, BindingResult result,
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
        adminService.registrazioneAdmin(admin);
        return "redirect:/admindash/creaAdmin?success";
    }

}