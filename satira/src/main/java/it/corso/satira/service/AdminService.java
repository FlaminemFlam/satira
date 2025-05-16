package it.corso.satira.service;


import it.corso.satira.model.Admin;
import jakarta.servlet.http.HttpSession;

public interface AdminService {
    String controlloLogin(String username, String password, HttpSession session);
    Admin datiAdmin(Integer id);
    String aggiuntaPost(Integer idPost, HttpSession session);
    void rimozionePost(Integer id, HttpSession session);
    void modificaPost(Integer id, Integer visible, HttpSession session);
    void registrazioneAdmin(Admin admin);
    String controlloUsername(String username);
}
