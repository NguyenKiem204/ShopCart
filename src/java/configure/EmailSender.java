/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configure;

/**
 *
 * @author nkiem
 */
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {
    public static void sendVerificationEmail(String toEmail, String token) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nkiem347@gmail.com", "fegwgmgicukzidsn");
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("nkiem347@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Xác minh tài khoản của bạn", "UTF-8");

        message.setContent("Vui lòng nhấn vào đường dẫn sau để xác minh tài khoản: "
            + "http://localhost:8080/ngkiemshopping/verify?token=" + token, "text/html; charset=UTF-8");

        Transport.send(message);
    }
}

