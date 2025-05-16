package it.corso.satira.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.satira.model.Admin;
import it.corso.satira.model.Commento;
import it.corso.satira.model.Post;
import it.corso.satira.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService{
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PostService postService;
    
    @Override
    public String controlloLogin(String username, String password, HttpSession session) {
        Admin admin = adminRepository.findByUsername(username);
        if(admin == null || !admin.getPassword().equals(password)){
            return "Credenziali Errate";
        }
        session.setAttribute("admin", admin);
        return "Login Autorizzato";
    }
    
    @Override
    public Admin datiAdmin(Integer id) {
        return adminRepository.findById(id).get();
    }

    @Override
public String aggiuntaPost(Integer idPost, HttpSession session) {
    // Recupera dal service il Post da inserire 
    Post postDaInserire = postService.datiPost(idPost);

    // Recupera l'admin dalla sessione
    Admin adminSessione = (Admin) session.getAttribute("admin");
    
    // Recupero l'oggetto completo Admin dal repository, in modo da avere tutte le relazioni correttamente caricate
    Optional<Admin> adminOpt = adminRepository.findById(adminSessione.getId());
    if (!adminOpt.isPresent()) {
        return "Admin non trovato in archivio.";
    }
    Admin admin = adminOpt.get();

    postDaInserire.setAdmin(admin);

    if (admin.getPost() == null) {
        admin.setPost(new ArrayList<>());
    }
    admin.getPost().add(postDaInserire);
    
    adminRepository.save(admin);  

    return "Post aggiunto con successo.";
}

    
    @Override
    public void rimozionePost(Integer id, HttpSession session) {
        // Recupero Admin
        Admin adminSessione = (Admin) session.getAttribute("admin");
        Admin admin = adminRepository.findById(adminSessione.getId()).get();
        // Individuo il post da eliminare
        for(Post post : admin.getPost()){
            if(post.getId().equals(id)){
                admin.getPost().remove(id);
                break;
            }
        }
    }
    
@Override
public void modificaPost(Integer id, Integer visible, HttpSession session) {
    
    Admin adminSessione = (Admin) session.getAttribute("admin");
    if (adminSessione == null) {
        return;
    }

    Admin admin = adminRepository.findById(adminSessione.getId()).orElse(null);
    if (admin == null) {
        return;
    }
    
    for (Post post : admin.getPost()) {
        if (post.getId().equals(id)) {
            
            List<Commento> commentiEsistenti = post.getCommento();
            
            if (commentiEsistenti == null) {
                commentiEsistenti = new ArrayList<>();
                post.setCommento(commentiEsistenti);
            }
            
            post.setDataPubblicazione(LocalDateTime.now());
            post.setVisible(visible);
            post.setAdmin(admin);
            
            break;
        }
    }
    adminRepository.save(admin);
}


    
    @Override
    public String controlloUsername(String username) {
        if(adminRepository.findByUsername(username) != null){
            return "Username Non Disponibile";
        }
        return null;
    }

    @Override
    public void registrazioneAdmin(Admin admin) {
        adminRepository.save(admin);
    }


}


