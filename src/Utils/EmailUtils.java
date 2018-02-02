package Utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {
    public void sendPlainTextEmail(String host, String port,
                                   final String userName, final String password, String toAddress,
                                   String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setText(message);

        // sends the e-mail
        Transport.send(msg);

    }

    public static void send (String mailTo, String subject, String message) {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "garmash.g.k@gmail.com";
        String password = "|_|_|JL|-0}{@";

        // outgoing message information
//        mailTo = "garmash.g.k@gmail.com";
//        String subject = "Hello my friend";
//        String message = "Hi guy, Hope you are doing well. Duke.";

        EmailUtils mailer = new EmailUtils();

        try {
            mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo,
                    subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
}