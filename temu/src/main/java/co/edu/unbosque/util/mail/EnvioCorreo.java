package co.edu.unbosque.util.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

// ¡OJO!: paquetes de Jakarta Mail
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Named
@RequestScoped
public class EnvioCorreo {

    private static String emailFrom = "temuverifycated@gmail.com";
    // usa aquí tu App Password de Google (16 caracteres)
    private static String passwordFrom = "diwq ecdq kank hgfa";

    private String emailTo;
    private String subject;
    private String content;

    private Properties mPropertie;
    private Session mSession;
    private MimeMessage mCorreo;

    public EnvioCorreo() {
        mPropertie = new Properties();
    }

    public void createEmail(String emailTo, String subject, String content) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.content = content;

        // SMTP Gmail TLS
        mPropertie.put("mail.smtp.host", "smtp.gmail.com");
        mPropertie.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mPropertie.setProperty("mail.smtp.starttls.enable", "true");
        mPropertie.setProperty("mail.smtp.port", "587");
        mPropertie.setProperty("mail.smtp.auth", "true");
        mPropertie.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // Autenticación
        mSession = Session.getInstance(mPropertie, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(emailFrom, passwordFrom);
            }
        });

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject, "UTF-8");
            mCorreo.setContent(content, "text/html; charset=UTF-8");
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No se pudo crear el correo", ex);
        }
    }

    public void sendEmail() {
        try {
            // Con Authenticator basta con Transport.send
            Transport.send(mCorreo);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No se pudo enviar el correo", ex);
        }
    }

    // getters/setters si los necesitas…
}
