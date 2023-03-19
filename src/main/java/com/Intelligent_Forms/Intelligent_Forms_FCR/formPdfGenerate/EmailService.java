package com.Intelligent_Forms.Intelligent_Forms_FCR.formPdfGenerate;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text, FileSystemResource file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom("test@test.com");
        helper.setSubject("Formular cerere înmatriculare");
        helper.setText("Vă rugăm găsiți atașat formularul cererii de înmatriculare.");
        helper.addAttachment("Formular cerere înmatriculare.pdf", file);


        javaMailSender.send(message);
    }

}