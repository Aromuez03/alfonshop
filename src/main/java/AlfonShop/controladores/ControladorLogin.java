package AlfonShop.controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.usuarioRepoImpl;
import AlfonShop.util.Encriptaciones;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorLogin {
	
	// Crear una instancia de Logger para la clase ControladorLogin
	private static final Logger logger = LoggerFactory.getLogger(ControladorLogin.class);

    @Autowired
    private usuarioRepoImpl repoUsuarios;

    // Este metodo muestra la ventana de login.
    @GetMapping("/Login")
    public String mostrarFormularioLogin(Model model) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioLogin\" en la clase \"ControladorLogin\"");
    	try 
    	{
    		model.addAttribute("usuario", new usuarioDto());
    	}
    	catch (Exception e) 
    	{
			System.out.println("[ERROR] Ha ocurrido un error: "+e);
			// Imprimir mensaje de error en la consola
		}
        
        return "Login";
        // Retornar la vista "Login"
    }

    @PostMapping("/Login")
    public String procesarFormularioLogin(@ModelAttribute usuarioDto usuarioDto,HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"procesarFormularioLogin\" en la clase \"ControladorLogin\"");

    	
    	// Escapar los valores de entrada del usuario
        String email = StringEscapeUtils.escapeHtml4(usuarioDto.getEmail());
        String clave = StringEscapeUtils.escapeHtml4(usuarioDto.getClave());

        // Validar y filtrar los valores de entrada del usuario
        if (email.isEmpty() || clave.isEmpty()) {
            // Si alguno de los valores de entrada es vacío, redirigir de vuelta al formulario de login
            return "Login";
        }
    	
        // Obtener la lista de usuarios
        List<usuarioDto> usuarios = repoUsuarios.buscarTodosLosUsuarios();

        // Buscar el usuario con el email ingresado
        usuarioDto usuarioEncontrado = null;
        for (usuarioDto u : usuarios) {
            if (u.getEmail().equals(usuarioDto.getEmail())) {
                usuarioEncontrado = u;
                usuarios = null;
                break;
            }
        }
        if(usuarioEncontrado == null) {
        	// Si no, se vuelve a mostrar el formulario de login con un mensaje de error
            return "redirect:/Login?error=1";
        }
        
        // Comprobar si se encontró el usuario y si la clave es correcta
        if (usuarioEncontrado.getNombre() != null && usuarioEncontrado.getClave().equals(Encriptaciones.encriptar(usuarioDto.getClave()))) {
        	
        	// Si el usuario existe y la contraseña es correcta, se guarda el rol del usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogeado", usuarioEncontrado);
            // Se redirige a la página de catálogo
            return "redirect:/bienvenida";
        } else {
            
        	// Si no, se vuelve a mostrar el formulario de login con un mensaje de error
            return "redirect:/Login?error=1";
        }
        
    }
    @GetMapping("/Logout")
    public String realizarLogout(HttpServletRequest request) {
		
		// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"realizarLogout\" en la clase \"ControladorLogin\"");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/Login";
        // Retornar la vista "Login"
    }

}
