package AlfonShop.controladores;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import AlfonShop.dao.rol;
import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.rolRepoImpl;
import AlfonShop.impl.usuarioRepoImpl;
import AlfonShop.util.Encriptaciones;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ControladorUsuarios {
	
	// Crear una instancia de Logger para la clase ControladorUsuarios
	private static final Logger logger = LoggerFactory.getLogger(ControladorUsuarios.class);

	@Autowired
	private usuarioRepoImpl repoUsuario;
	
	@Autowired
	private rolRepoImpl repoRoles;
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"listarUsuarios\" en la clase \"ControladorUsuarios\"");
		
	    // Obtener el rol del usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si es el rol necesario
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        List<usuarioDto> usus1 = repoUsuario.buscarTodosLosUsuarios();
	        List<usuarioDto> usus2 = new ArrayList<>();
	        for (usuarioDto usuario2 : usus1) {
	            usus2.add(usuario2);
	        }
	        model.addAttribute("usuarios", usus2);
	        String palabraGenerada = generarPalabraAleatoria();
	        model.addAttribute("palabraGenerada", palabraGenerada);
	        return "usuarios";
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}


	@GetMapping("/usuario/{id}")
	public String verUsuario(@PathVariable int id, Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"verUsuario\" en la clase \"ControladorUsuarios\"");
		
	    // Obtener el rol del usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si es el rol necesario
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        usuarioDto usu = repoUsuario.buscarUsuarioPorId(id);
	        model.addAttribute("usuario", usu);
	        return "perfilusuario";
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}


	@GetMapping("/usuario/editar/{id}")
	public String editarUsuario(@PathVariable int id, Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"editarUsuario\" en la clase \"ControladorUsuarios\"");
		
	    // Obtener el rol del usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si es el rol necesario
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        usuarioDto usu = repoUsuario.buscarUsuarioPorId(id);
	        if (usu.getRol().getNombre().equals("superadmin")) {
	            return "redirect:/usuarios";
	        }
	        usuarioDto usu2 = new usuarioDto();
	        usu2.setClave(Encriptaciones.desencriptar(usu.getClave()));
	        usu2.setEmail(usu.getEmail());
	        usu2.setId(usu.getId());
	        usu2.setNombre(usu.getNombre());
	        usu2.setTelefono(usu.getTelefono());
	        usu2.setRol(usu.getRol());
	        usu2.setVerificado(usu.getVerificado());
	        model.addAttribute("usuario", usu2);

	        return "editarUsuario";
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}


	@PostMapping("/usuario/guardar")
	public String guardarUsuario(usuarioDto usuDTO, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"guardarUsuario\" en la clase \"ControladorUsuarios\"");
		
	    // Obtener el rol del usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si es el rol necesario
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    
	    if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        
	        usuarioDto usu = new usuarioDto();
	        usuarioDto usu2 = repoUsuario.buscarUsuarioPorId(usuDTO.getId());
	        usu.setId(usuDTO.getId());
	        usu.setNombre(usuDTO.getNombre());
	        usu.setClave(usu2.getClave());
	        usu.setEmail(usuDTO.getEmail());
	        usu.setTelefono(usuDTO.getTelefono());
	        usu.setRol(usuDTO.getRol());
	        usu.setVerificado(usuDTO.getVerificado());

	        // Obtener el rol seleccionado por su ID
	        int rolId = usuDTO.getRol().getId();
	        rol rolSeleccionado = repoRoles.buscarRolPorId(rolId);
	        
	        usu.setRol(rolSeleccionado);
	        
	        String nombre = usu.getRol().getNombre();
	        if (nombre.equals("superadmin")) {
	            return "redirect:/usuarios";
	        }
	        repoUsuario.actualizarUsuario(usu);

	        return "redirect:/usuario/" + usu.getId();
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}


	@GetMapping("/usuario/eliminar/{id}")
	public String eliminarUsuario(@PathVariable int id, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"eliminarUsuario\" en la clase \"ControladorUsuarios\"");
		
	    // Obtener el rol del usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si es el rol necesario
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        usuarioDto usu = repoUsuario.buscarUsuarioPorId(id);
	        String nombre = usu.getRol().getNombre();
	        if (nombre.equals("superadmin")) {
	            return "redirect:/usuarios";
	        }
	        // se elimina el usuario en bd.
	        repoUsuario.eliminarUsuario(id);
	        // se redirecciona nuevamente a la lista
	        return "redirect:/usuarios";
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}


    private String generarPalabraAleatoria() {
    	
    	// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"generarPalabraAleatoria\" en la clase \"ControladorUsuarios\"");
    	
        // Lógica para generar una palabra aleatoria

        // Generar una palabra aleatoria de 6 caracteres
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder palabra = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int indice = (int) (Math.random() * caracteres.length());
            palabra.append(caracteres.charAt(indice));
        }

        return palabra.toString();
    }


}
