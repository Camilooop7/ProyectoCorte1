package co.edu.unbosque.util.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Servicio para enviar correos electrónicos.
 */
@Named
@RequestScoped
public class EnvioCorreo {

	/** Correo electrónico del remitente. */
	private static String emailFrom = "temuverifycated@gmail.com";

	/** Contraseña del remitente. */
	private static String passwordFrom = "diwq ecdq kank hgfa";

	/** Correo electrónico del destinatario. */
	private String emailTo;

	/** Asunto del correo. */
	private String subject;

	/** Contenido del correo. */
	private String content;

	/** Propiedades para la configuración del correo. */
	private Properties mPropertie;

	/** Sesión de correo. */
	private Session mSession;

	/** Mensaje de correo. */
	private MimeMessage mCorreo;

	/**
	 * Constructor por defecto.
	 */
	public EnvioCorreo() {
		mPropertie = new Properties();
	}

	/**
	 * Crea un correo electrónico con los parámetros proporcionados.
	 * 
	 * @param emailTo Correo del destinatario.
	 * @param subject Asunto del correo.
	 * @param content Contenido del correo.
	 */
	public void createEmail(String emailTo, String subject, String content) {
		this.emailTo = emailTo;
		this.subject = subject;
		this.content = content;
		mPropertie.put("mail.smtp.host", "smtp.gmail.com");
		mPropertie.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		mPropertie.setProperty("mail.smtp.starttls.enable", "true");
		mPropertie.setProperty("mail.smtp.port", "587");
		mPropertie.setProperty("mail.smtp.auth", "true");
		mPropertie.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
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

	/**
	 * Envía el correo electrónico.
	 */
	public void sendEmail() {
		try {
			Transport.send(mCorreo);
		} catch (MessagingException ex) {
			Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException("No se pudo enviar el correo", ex);
		}
	}
}
