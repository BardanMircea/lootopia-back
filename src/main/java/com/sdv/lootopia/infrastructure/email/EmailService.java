package com.sdv.lootopia.infrastructure.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public void sendActivationEmail(String to, String activationToken) {
        String activationUrl = "https://localhost:4200/activate?token=" + activationToken;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Activez votre compte Lootopia");
        message.setText("Bienvenue sur Lootopia ! Cliquez ici pour activer votre compte :\n" + activationUrl);
        message.setFrom(env.getProperty("spring.mail.username"));

        mailSender.send(message);
    }
}
