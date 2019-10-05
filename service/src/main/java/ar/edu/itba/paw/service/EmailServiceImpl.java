package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;


    //for text or html
    @Override
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


    //guardar el test


    //for emails with images
    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String classPath) {
        MimeMessage message = emailSender.createMimeMessage();

        //ver si el encoding ya esta en config
        try{
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        //FileSystemResource file = new FileSystemResource(new File(classPath));
        //helper.addAttachment("mailPage", file);
        helper.addAttachment(classPath,new ClassPathResource(classPath)); }catch (Exception e){
            //do something
        }

        emailSender.send(message);
    }
    @Override
    public void sendFormatedMessage(String to,String subject,String content,String resourcePath) {
        MimeMessage message = emailSender.createMimeMessage();

        try {
        //ver si el encoding ya esta en config
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText("<html><body><p>" + content + "</p><img src='cid:resource'></body></html>", true);
        helper.addInline("resource", new ClassPathResource(resourcePath)); } catch (Exception e){
            //do the same thing
        }

        emailSender.send(message);
    }
}
