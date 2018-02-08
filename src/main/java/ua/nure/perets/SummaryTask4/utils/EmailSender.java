package ua.nure.perets.SummaryTask4.utils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    private static final Logger LOG = Logger.getLogger(EmailSender.class);

    /**
     * registration confirmation method
     *
     * @param sendTo recipient's email address
     * @param name   receiver name
     */
    public static void sender(String sendTo, String name) {

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aleksey.perets@gmail.com", "rrzttyghnzbdyslp");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
            message.setSubject(name + ", Вы удачно зарегистрировались на нашем сайте.");
            message.setText("Test Mail");
            Transport.send(message);
            log("Email sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void log(String msg) {
        System.out.println("[EmailSender] " + msg);
    }
}


