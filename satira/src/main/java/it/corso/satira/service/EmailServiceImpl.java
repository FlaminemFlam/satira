package it.corso.satira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.corso.satira.model.BecomeAdmin;
import it.corso.satira.repository.BecomeAdminRepository;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BecomeAdminRepository becomeAdminRepository;

    @Override
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
    }

    @Override
    public BecomeAdmin findByToken(String token) {
        return becomeAdminRepository.findByToken(token);
    }

    @Override
    public void saveRequest(String username, String email, String token) {
        BecomeAdmin request = new BecomeAdmin();
        becomeAdminRepository.save(request);
    }

    @Override
    public void confirmRequest(BecomeAdmin request) {
        request.setConfirmed(true);
        becomeAdminRepository.save(request);
    }


}
