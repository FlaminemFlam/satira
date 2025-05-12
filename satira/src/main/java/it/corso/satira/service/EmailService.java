package it.corso.satira.service;

import it.corso.satira.model.BecomeAdmin;

public interface EmailService {
    void sendMail(String to, String subject, String body);
    BecomeAdmin findByToken(String token);
    void saveRequest(String username, String email, String token);
    void confirmRequest(BecomeAdmin request);
}
