package ar.edu.itba.paw.interfaces.service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public interface EmailService {
    void sendSimpleMail(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) ;

    void sendFormatedMessage(String to,String subject,String content,String resourcePath);
}
