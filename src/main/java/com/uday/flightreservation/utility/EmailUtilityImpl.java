package com.uday.flightreservation.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtilityImpl implements EmailUtility {

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendMail(String toAddress, String subject, String body) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message);

        try {
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body);
            System.out.println("Inside sending mail to "+toAddress);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendAttachmentMail(String toAddress, String subject, String body, String attachmentPath) {

        MimeMessage message = sender.createMimeMessage();


        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.addAttachment("Flight_Itinerary",new File(attachmentPath));
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
