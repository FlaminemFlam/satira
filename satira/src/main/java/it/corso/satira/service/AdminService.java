package it.corso.satira.service;

import it.corso.satira.model.Admin;
import jakarta.servlet.http.HttpSession;

public interface AdminService {
    String controlloLogin(String username, String password, HttpSession session);
    Admin datiAdmin(Integer id);
}
