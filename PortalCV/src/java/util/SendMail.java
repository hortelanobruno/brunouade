/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 *
 * @author bruno
 */
public class SendMail implements Runnable{

    private String from;
    private String username;
    private String password;
    private String smtpServer;
    private String to;
    private String subject;
    private String txt;

    public SendMail() {
    }

    public SendMail(String smtpServer, String from, String to, String subject, String txt) {
        this.smtpServer = smtpServer;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.txt = txt;
    }

    public void postGMail(String to, String subject, String txt) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.connectiontimeout", "30000");
        props.put("mail.smtp.timeout", "30000");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setFrom(new InternetAddress(from));
        message.setContent(txt, "text/html");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));

        Transport.send(message);
    }

    public void postMail(String smtpServer, String from, String to, String subject, String txt) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.from", from);

        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setFrom(new InternetAddress(from));
        message.setContent(txt, "text/html; charset=iso-8859-1");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));

        Transport.send(message);
    }

    @Override
    public void run() {
        try {
            postMail(smtpServer, from, to, subject, txt);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
