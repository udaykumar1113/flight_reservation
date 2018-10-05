package com.uday.flightreservation.utility;

public interface EmailUtility {
    void sendMail(String sender, String subject, String body);
    void sendAttachmentMail(String sender, String subject, String body, String attachmentPath);
}
