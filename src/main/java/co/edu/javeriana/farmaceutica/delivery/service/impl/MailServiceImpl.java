package co.edu.javeriana.farmaceutica.delivery.service.impl;

import co.edu.javeriana.farmaceutica.delivery.service.MailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        emailSender.send(message);
    }
}
