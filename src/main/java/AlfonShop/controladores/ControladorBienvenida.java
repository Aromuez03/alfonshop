package AlfonShop.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import AlfonShop.dto.usuarioDto;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ControladorBienvenida {
	
	// Crear una instancia de Logger para la clase ControladorBienvenida
	private static final Logger logger = LoggerFactory.getLogger(ControladorBienvenida.class);
	
	@GetMapping("/bienvenida")
    public String mostrarBienvenida(Model model, HttpServletRequest request) {
		try 
    	{
			
			// Registra en los logs la entrada al método
			logger.info("[INFORMACION]: Entrando en el método \"mostrarBienvenida\" en la clase \"ControladorBienvenida\"");
	
			// Obtener el usuario desde la sesión
		    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");
	
		    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
		    if (user == null || user.getRol() == null) {
		    	model.addAttribute("rolUsuario", "nope");
		    }
		    else {
		    	String rolUsuario = user.getRol().getNombre();
		    	model.addAttribute("rolUsuario", rolUsuario);
		    }
	    

    	}
    	catch (Exception e) 
    	{
			System.out.println("[ERROR] Ha ocurrido un error: "+e);
			// Muestra el mensaje de error en la consola
		}
        
        return "bienvenida";
        // Devuelve el nombre de la vista "bienvenida"
    }
}
