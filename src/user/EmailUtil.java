/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;


    import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
    
    public static void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath)
            throws MessagingException, IOException {
        
        // Configuration du serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // remplacer par le nom de votre serveur SMTP
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Authentification
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("alaedineibrahim@gmail.com", "");
                // remplacer par votre adresse e-mail et mot de passe de connexion
            }
        });
        
        // Création du message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("alaedineibrahim@gmail.com")); // remplacer par votre adresse e-mail
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("test");
        message.setSentDate(new Date());
        
        // Corps du message
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");
        
        // Pièce jointe
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (attachmentPath != null && !attachmentPath.isEmpty()) {
            File attachment = new File(attachmentPath);
            if (attachment.exists()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(attachment.getName());
                multipart.addBodyPart(attachmentPart);
            } else {
                System.err.println("Attachment file not found: " + attachmentPath);
            }
        }
        message.setContent(multipart);
        
        // Envoi du message
        Transport.send(message);
    }
}

    

