package AlfonShop.controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AlfonShop.dao.estado;
import AlfonShop.dto.sugerenciaDto;
import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.estadoRepoImpl;
import AlfonShop.impl.sugerenciaRepoImpl;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ControladorSugerencia {
	
	// Crear una instancia de Logger para la clase ControladorSugerencia
	private static final Logger logger = LoggerFactory.getLogger(ControladorSugerencia.class);

    @Autowired
    private sugerenciaRepoImpl sugerenciaService;
    
    @Autowired
    private estadoRepoImpl estadoService;


    @GetMapping("/BuzonSugerencias")
    public String mostrarBuzonSugerencias(Model model, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarBuzonSugerencias\" en la clase \"ControladorSugerencia\"");
    	
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
            // Obtener todas las sugerencias con estadoId 1 del servicio
            List<sugerenciaDto> sugerencias = sugerenciaService.buscarTodasLasSugerencias();

            // Agregar las sugerencias al modelo
            model.addAttribute("sugerencias", sugerencias);

            return "sugerencias";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }

    @GetMapping("/editarSugerencia")
    public String mostrarFormularioEdicionSugerencia(@RequestParam int id, Model model, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioEdicionSugerencia\" en la clase \"ControladorSugerencia\"");
    	
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
            // Obtener la sugerencia a editar por su ID
            sugerenciaDto sugerencia = sugerenciaService.buscarSugerenciaPorId(id);

            // Agregar la sugerencia al modelo
            model.addAttribute("sugerenciaDto", sugerencia);

            return "EditarSugerencia";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }

    @PostMapping("/editarSugerencia")
    public String procesarFormularioEdicionSugerencia(@ModelAttribute sugerenciaDto sugerenciaDto, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"procesarFormularioEdicionSugerencia\" en la clase \"ControladorSugerencia\"");
    	
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
            // Obtener la sugerencia a editar por su ID
            sugerenciaDto sugerenciaExistente = sugerenciaService.buscarSugerenciaPorId(sugerenciaDto.getId());

            // Actualizar los datos de la sugerencia existente con los datos del formulario
            sugerenciaExistente.setMensaje(sugerenciaDto.getMensaje());
            sugerenciaExistente.setEstado(sugerenciaDto.getEstado());

            // Guardar los cambios en la sugerencia
            sugerenciaService.guardarSugerencia(sugerenciaExistente);

            // Redirigir al buzón de sugerencias después de editar la sugerencia
            return "redirect:/BuzonSugerencias";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }

    @GetMapping("/NuevaSugerencia")
    public String mostrarFormularioSugerencia(Model model, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioSugerencia\" en la clase \"ControladorSugerencia\"");
    	
        // Obtener el rol del usuario desde la sesión
        usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

        // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
        if (user == null || user.getRol() == null) {
            return "redirect:/Login?error2=1";
        }

        // Comprobar si es el rol necesario
        String rolUsuario = user.getRol().getNombre();
        boolean verificado = user.getVerificado();
        if ((rolUsuario.equals("usuario") || rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
            // Agregar un nuevo objeto sugerenciaDto al modelo
            model.addAttribute("sugerenciaDto", new sugerenciaDto());
            model.addAttribute("rolUsuario", rolUsuario);

            return "NuevaSugerencia";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }

    @PostMapping("/NuevaSugerencia")
    public String procesarFormularioSugerencia(@ModelAttribute sugerenciaDto sugerenciaDto, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"procesarFormularioSugerencia\" en la clase \"ControladorSugerencia\"");
    	
        // Obtener el rol del usuario desde la sesión
        usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

        // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
        if (user == null || user.getRol() == null) {
            return "redirect:/Login?error2=1";
        }

        // Comprobar si es el rol necesario
        String rolUsuario = user.getRol().getNombre();
        boolean verificado = user.getVerificado();
        if ((rolUsuario.equals("usuario") || rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
            estado enRevision = new estado();
            List<estado> estados = estadoService.buscarTodosLosEstados();
            for (estado estado1 : estados) {
                if (estado1.getNombre().equals("enrevision")) {
                    enRevision = estado1;
                }
            }
            sugerenciaDto.setEstado(enRevision);
            sugerenciaService.guardarSugerencia(sugerenciaDto);

            // Redirigir al buzón de sugerencias después de guardar la sugerencia
            return "redirect:/bienvenida";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }

}
