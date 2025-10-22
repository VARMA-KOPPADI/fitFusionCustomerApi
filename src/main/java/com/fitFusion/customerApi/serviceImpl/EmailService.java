package com.fitFusion.customerApi.serviceImpl;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String body, String to) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setTo(to);
            mailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
