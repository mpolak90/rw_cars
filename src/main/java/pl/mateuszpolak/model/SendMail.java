package pl.mateuszpolak.model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendMail {

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final String FROM = "classiccarsdoc@gmail.com";
    private static final String PASSWORD = "*****";
    private static final String TO = "classiccarsdoc@gmail.com";

    public static void execute(String subject, String content) {
        try {
            new SendMail().send(subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void send(String subject, String content) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");

        Session mailSession = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(mailSession);

        message.setSubject(subject, "UTF-8");
        message.setContent(content, "text/html; charset=UTF-8");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

        Transport transport = mailSession.getTransport();
        transport.connect(HOST, PORT, FROM, PASSWORD);

        transport.sendMessage(message, message
                .getRecipients(Message.RecipientType.TO));
        transport.close();
    }
}