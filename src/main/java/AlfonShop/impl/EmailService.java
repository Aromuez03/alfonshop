package AlfonShop.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviarCorreo(String destinatario, String asunto, String contenido) {
        try {
            // Configuración del servidor SMTP de Gmail
            String servidorSMTP = "smtp.ionos.es";
            int puertoSMTP = 587;
            String usuario = "info@alfromuez.es";
            String contrasena = "";

            // Creación de la sesión de correo electrónico
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", servidorSMTP);
            props.put("mail.smtp.port", puertoSMTP);

            Session sesion = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, contrasena);
                }
            });

            // Creación del mensaje de correo electrónico
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, "text/html"); // Utilizar contenido HTML en el correo

            // Envío del mensaje de correo electrónico
            Transport.send(mensaje);
        } catch (Exception e) {
            System.out.println("[ERROR]: " + e);
        }
    }

}
