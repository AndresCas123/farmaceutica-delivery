package co.edu.javeriana.farmaceutica.delivery.service;

import javax.mail.MessagingException;

public interface MailService {
    void send(String to, String subject, String body) throws MessagingException;
}
