package AlfonShop.controladores;

import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.usuarioRepoImpl;
import AlfonShop.util.Encriptaciones;


@Controller
public class ControladorRecuperador {
	
	// Crear una instancia de Logger para la clase ControladorRecuperador
	private static final Logger logger = LoggerFactory.getLogger(ControladorRecuperador.class);

	 @Autowired
	   private usuarioRepoImpl repoUsuarios;

	
	@GetMapping("/RecuperarContrasena")
    public String mostrarFormularioRecuperarContrasena() {
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioRecuperarContrasena\" en la clase \"ControladorRecuperador\"");
		
        return "RecuperarContrasena";
    }

    @PostMapping("/RecuperarContrasena")
    public String procesarFormularioRecuperarContrasena(@RequestParam("email") String email) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"RecuperarContrasena\" en la clase \"ControladorRecuperador\"");
		
        // Verificar si el correo electrónico es válido y si existe en la base de datos
    	// Obtener la lista de usuarios
        List<usuarioDto> usuarios = repoUsuarios.buscarTodosLosUsuarios();

        // Buscar el usuario con el email ingresado
        usuarioDto usuarioEncontrado = null;
        for (usuarioDto u : usuarios) {
            if (u.getEmail().equals(email)) {
                usuarioEncontrado = u;
                usuarios = null;
                break;
            }
        }
        if (usuarioEncontrado == null) {
            return "redirect:/Login?recuperacion=1";
            
        } else {
            // Generar una nueva contraseña aleatoria
            String nuevaContrasena = generarContrasenaAleatoria();
            String nombre = usuarioEncontrado.getRol().getNombre();
	        if (nombre.equals("superadmin")) {
	            return "redirect:/Login";
	        }
            usuarioEncontrado.setClave(Encriptaciones.encriptar(nuevaContrasena));
            repoUsuarios.actualizarUsuario(usuarioEncontrado);
            String mail = usuarioEncontrado.getEmail();
            // Enviar el correo electrónico con la nueva contraseña
            enviarCorreoRecuperacionContrasena(mail, nuevaContrasena);
            
            // Redirigir a la página de inicio de sesión con un mensaje de éxito
            return "redirect:/Login?recuperacion=1";
        }
    }
    private String generarContrasenaAleatoria() {
		
		// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"generarContrasenaAleatoria\" en la clase \"ControladorRecuperador\"");
		
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
    public void enviarCorreoRecuperacionContrasena(String destinatario, String nuevaContrasena){
		
		// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"enviarCorreoRecuperacionContrasena\" en la clase \"ControladorRecuperador\"");
    	try {
        // Configuración del servidor SMTP de ionos
        String servidorSMTP = "smtp.ionos.es";
        int puertoSMTP = 587;
        String usuario = "info@alfromuez.es";
        String contrasena = "ALF26tair#";

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
        mensaje.setSubject("Recuperación de contraseña");
        mensaje.setText("Tu nueva contraseña es: " + nuevaContrasena);

        // Envío del mensaje de correo electrónico
        Transport.send(mensaje);
    	}catch (Exception e) {
			System.out.println("[ERROR]: "+e);
		}
    }


}
