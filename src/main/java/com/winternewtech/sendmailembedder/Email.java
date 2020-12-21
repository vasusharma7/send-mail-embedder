package com.winternewtech.sendmailembedder;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
    public static void sendmail(users user, Map<String, Object> data)
            throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("feedbackservice85@gmail.com", "Temporarypw");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("feedbackservice85@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.email));
        msg.setSubject("Sample Email");
        msg.setContent(String.format("Hello %s", user.name), "text/html");
        msg.setSentDate(new Date());

        Multipart multipart = new MimeMultipart();

        for (Entry<String, Object> entry : data.entrySet()) {
            MimeBodyPart section = new MimeBodyPart();
            section.setContent(String.format("<b> %s </b> :", entry.getKey()), "text/html");
            multipart.addBodyPart(section);

            MimeBodyPart body = new MimeBodyPart();
            body.setContent(String.format("%s <br/><br/>", entry.getValue()), "text/html");
            multipart.addBodyPart(body);

        }

        // MimeBodyPart attachPart = new MimeBodyPart();
        // attachPart.attachFile("/var/tmp/image19.png");
        // multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }

}
