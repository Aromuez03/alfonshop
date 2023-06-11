package AlfonShop.controladores;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import AlfonShop.dao.rol;
import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.EmailService;
import AlfonShop.impl.rolRepoImpl;
import AlfonShop.impl.usuarioRepoImpl;
import AlfonShop.util.Encriptaciones;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorRegistro {
	
	// Crear una instancia de Logger para la clase ControladorRegistro
	private static final Logger logger = LoggerFactory.getLogger(ControladorRegistro.class);

    @Autowired
    private usuarioRepoImpl repoUsuarios;
    
    @Autowired
    private rolRepoImpl repoRol;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/Registro")
    public String mostrarFormularioRegistro(Model model) {
		
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioRegistro\" en la clase \"ControladorRegistro\"");
        
		try {
            model.addAttribute("usuario", new usuarioDto());
        } catch (Exception e) {
            System.out.println("[ERROR] Ha ocurrido un error: " + e);
        }
        return "Registro";
    }
    
    @GetMapping("/confirmar")
    public String confirmar(@RequestParam("token") String token, HttpSession session) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"confirmar\" en la clase \"ControladorRegistro\"");
    	
        // Obtener el token almacenado en sesión
        String tokenSesion = (String) session.getAttribute("registroToken");

        // Verificar si el token proporcionado coincide con el almacenado en sesión
        if (tokenSesion != null && tokenSesion.equals(token)) {
            // Obtener el usuario actual de la sesión
        	usuarioDto usuario = (usuarioDto) session.getAttribute("usuarioActual");
        	
        	
            // Verificar si el usuario existe
            if (usuario != null) {
            	usuarioDto usu = new usuarioDto();
            	List<usuarioDto> usus1 = repoUsuarios.buscarTodosLosUsuarios();
     	        for (usuarioDto usuario2 : usus1) {
     	            usu = usuario2;
     	        }
            	if(usu != null) {
                // Marcar al usuario como verificado
     	        usu.setVerificado(true);
                repoUsuarios.actualizarUsuario(usu);

                // Eliminar el token de sesión
                session.removeAttribute("registroToken");

                return "redirect:/Login";
            	}
            	else {
            		return "redirect:/bienvenida?needverificado=1";
            	}
            } else {
                return "redirect:/bienvenida?needverificado=1";
            }
        } else {
            return "redirect:/bienvenida?needverificado=1";
        }
    }



    @PostMapping("/Registro")
    public String procesarFormularioRegistro(@ModelAttribute usuarioDto usuarioDto, HttpSession session) {
    	
		// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"procesarFormularioRegistro\" en la clase \"ControladorRegistro\"");
    	
    	if (usuarioDto == null) {
            return "Registro";
        }
        // Obtener la lista de usuarios
        List<usuarioDto> listaUsuarios = repoUsuarios.buscarTodosLosUsuarios();
        
        // Buscar el usuario con el email ingresado
        usuarioDto usuarioExistente = null;
        for (usuarioDto u : listaUsuarios) {
            if (u.getEmail().equals(usuarioDto.getEmail())) {
                usuarioExistente = u;
                break;
            }
            if (u.getNombre().equals(usuarioDto.getNombre())) {
                usuarioExistente = u;
                break;
            }
        }
        // Comprobar si ya existe un usuario con ese email
        if (usuarioExistente != null) {
            // Si ya existe, se vuelve a mostrar el formulario de registro con un mensaje de error
            return "redirect:/Registro?error=1";
        } else {
            // Si no existe, se crea el usuario y se redirige a la página de catálogo

        	// Generar un token de confirmación
            String tokenConfirmacion = generarTokenConfirmacion();
            session.setAttribute("registroToken", tokenConfirmacion);
        	
            // Encriptar la contraseña antes de almacenarla
            String claveEncriptada = (String)Encriptaciones.encriptar(usuarioDto.getClave());
            rol userRol = repoRol.buscarRolPorId(1);
            
            // Crear un nuevo usuario con los datos proporcionados
            usuarioDto nuevoUsuario = new usuarioDto();
            nuevoUsuario.setNombre(usuarioDto.getNombre());
            nuevoUsuario.setClave(claveEncriptada);
            nuevoUsuario.setEmail(usuarioDto.getEmail());
            nuevoUsuario.setTelefono(usuarioDto.getTelefono());
            nuevoUsuario.setRol(userRol);
            nuevoUsuario.setVerificado(Boolean.FALSE);

            // Guardar el nuevo usuario en la base de datos
            repoUsuarios.actualizarUsuario(nuevoUsuario);
            
            // Envío del correo de confirmación
            enviarCorreoConfirmacion(nuevoUsuario, session);
            
            // Guardar el usuario actual en la sesión
            session.setAttribute("usuarioActual", nuevoUsuario);

            return "redirect:/bienvenida?needverificado=1";
        }
    }
    private String generarTokenConfirmacion() {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"generarTokenConfirmacion\" en la clase \"ControladorRegistro\"");
    	
        // Generar un UUID aleatorio
        UUID uuid = UUID.randomUUID();

        // Convertir el UUID a String
        String token = uuid.toString();

        return token;
    }

    private void enviarCorreoConfirmacion(usuarioDto usuario,HttpSession session) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"enviarCorreoConfirmacion\" en la clase \"ControladorRegistro\"");
    	
        // Construir el enlace de confirmación con el token de confirmación
        String enlaceConfirmacion = "https://shop.alfromuez.es/confirmar?token=" + session.getAttribute("registroToken");

        // Construir el contenido del correo electrónico
        String contenidoCorreo = "¡Gracias por registrarte en AlfonShop!\n\n";
        contenidoCorreo += "Para confirmar tu correo electrónico, haz clic en el siguiente enlace:\n";
        contenidoCorreo += "<a href=\"" + enlaceConfirmacion + "\">Confirmar correo</a>";

        // Enviar el correo electrónico al usuario
        emailService.enviarCorreo(usuario.getEmail(), "Confirmación de correo electrónico", contenidoCorreo);
    }

}
