package AlfonShop.controladores;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AlfonShop.dao.categoria;
import AlfonShop.dao.producto;
import AlfonShop.dto.ToDTO;
import AlfonShop.dto.productoDto;
import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.categoriaRepoImpl;
import AlfonShop.impl.productoRepoImpl;
import AlfonShop.repositorio.productoRepositorio;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ControladorCatalogo {
	
	// Crear una instancia de Logger para la clase ControladorCatalogo
	private static final Logger logger = LoggerFactory.getLogger(ControladorCatalogo.class);

    @Autowired
    private productoRepoImpl repoProductosImpl;
    
    @Autowired
    private categoriaRepoImpl repoCategorias;
    
    @Autowired
    private productoRepositorio repoProductos;
    
    @Autowired
    private ToDTO todto;

    @GetMapping("/Catalogo")
    public String mostrarCatalogo(@RequestParam(value = "categoriaId", required = false) Integer categoriaId, Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"mostrarCatalogo\" en la clase \"ControladorCatalogo\"");
		
    	// Obtener el usuario desde la sesión
        usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");
        
        // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
        if (user == null || user.getRol() == null) {
            return "redirect:/Login?error2=1";
        }
        // Obtener el rol y la verificación del usuario
        String rolUsuario = user.getRol().getNombre();
        boolean verificado = user.getVerificado();
        
        // Comprobar si el usuario tiene el rol adecuado y está verificado
        if ((rolUsuario.equals("usuario") || rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
            List<categoria> categorias = repoCategorias.listarCategorias();
            model.addAttribute("categorias", categorias);
            model.addAttribute("rolUsuario", rolUsuario);
	        String palabraGenerada = generarPalabraAleatoria();
	        model.addAttribute("palabraGenerada", palabraGenerada);
            List<productoDto> productos = new ArrayList<>();
            List<producto> productosPorCategoria;
            if (categoriaId != null) {
                categoria categoria = repoCategorias.obtenerCategoriaPorId(categoriaId);
                productosPorCategoria = repoProductos.findByCategoria(categoria);
                for (producto prod : productosPorCategoria) {
                	productos.add(todto.ToDTOprod(prod));
                }
                model.addAttribute("categoriaSeleccionada", categoriaId);
            } else {
                productos = repoProductosImpl.obtenerTodosLosProductos();
                model.addAttribute("categoriaSeleccionada", 0);
            }
            model.addAttribute("productos", productos);
            return "Catalogo";
        } else {
            return "redirect:/bienvenida?error=1";
        }
    }

    @GetMapping("/Catalogo/{categoriaId}")
    public String mostrarProductosPorCategoria(@PathVariable int categoriaId, Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"mostrarProductosPorCategoria\" en la clase \"ControladorCatalogo\"");
        
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
            categoria categoria = repoCategorias.obtenerCategoriaPorId(categoriaId);
            List<producto> productos = repoProductos.findByCategoria(categoria);
            model.addAttribute("productos", productos);
            String categoriaSeleccionada = String.valueOf(categoriaId);
            model.addAttribute("rolUsuario", rolUsuario);
            model.addAttribute("categoriaSeleccionada", categoriaSeleccionada);
	        String palabraGenerada = generarPalabraAleatoria();
	        model.addAttribute("palabraGenerada", palabraGenerada);
            return "Catalogo";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }



    @GetMapping("/Catalogo/CrearProducto")
    public String mostrarFormularioCrearProducto(Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioCrearProducto\" en la clase \"ControladorCatalogo\"");
        
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
            List<categoria> categorias = repoCategorias.listarCategorias();
            model.addAttribute("categorias", categorias);
            model.addAttribute("rolUsuario", rolUsuario);
            return "CrearProducto";
        } else {
        
            return "redirect:/bienvenida?error=1";
        }
    }

    @PostMapping("/Catalogo/GuardarProducto")
    public String guardarProducto(@RequestParam("nombre") String nombre,
            @RequestParam("precio") int precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("categoria") int categoriaId,@RequestParam("urlImg") String url,
            @RequestParam("descripcion") String descripcion,
            Model model, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"guardarProducto\" en la clase \"ControladorCatalogo\"");
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
        	categoria categoria = repoCategorias.obtenerCategoriaPorId(categoriaId);
            productoDto producto = new productoDto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setUrlImg(url);
            producto.setDescripcion(descripcion);
            producto.setCategoria(categoria);
            repoProductosImpl.guardarProducto(producto);
            return "redirect:/Catalogo";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }
    
    @GetMapping("/Catalogo/EditarProducto/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable int id, Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"mostrarFormularioEditarProducto\" en la clase \"ControladorCatalogo\"");
        
        // Obtener el rol del usuario desde la sesión
    	usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");
        
        if (user == null || user.getRol() == null) {
            return "redirect:/Login?error2=1";
        }
        String rolUsuario = user.getRol().getNombre();
        boolean verificado = user.getVerificado();
        if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
            productoDto producto = repoProductosImpl.obtenerProductoPorId(id);
            List<categoria> categorias = repoCategorias.listarCategorias();
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categorias);
            model.addAttribute("rolUsuario", rolUsuario);
            return "EditarProducto";
        } else {
            return "redirect:/bienvenida?error=1";
        }
    }

    @PostMapping("/Catalogo/ActualizarProducto/{id}")
    public String actualizarProducto(@PathVariable int id,
            @RequestParam("nombre") String nombre,
            @RequestParam("precio") int precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("categoria") int categoriaId,
            @RequestParam("urlImg") String url,
            @RequestParam("descripcion") String descripcion,
            Model model, HttpServletRequest request) {
    	
		// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"actualizarProducto\" en la clase \"ControladorCatalogo\"");
        
    	// Obtener el rol del usuario desde la sesión
    	usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");
        if (user == null || user.getRol() == null) {
            return "redirect:/Login?error2=1";
        }
        String rolUsuario = user.getRol().getNombre();
        boolean verificado = user.getVerificado();
        if ((rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
            categoria categoria = repoCategorias.obtenerCategoriaPorId(categoriaId);
            productoDto producto = repoProductosImpl.obtenerProductoPorId(id);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.setUrlImg(url);
            producto.setDescripcion(descripcion);
            producto.setCategoria(categoria);
            repoProductosImpl.guardarProducto(producto);
            return "redirect:/Catalogo";
        } else {
            return "redirect:/bienvenida?error=1";
        }
    }

    
    @GetMapping("/Catalogo/EliminarProducto/{id}")
    public String eliminarProducto(@PathVariable int id, Model model, HttpServletRequest request) {
		
    	// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"eliminarProducto\" en la clase \"ControladorCatalogo\"");
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
            repoProductosImpl.eliminarProducto(id);
            return "redirect:/Catalogo";
        } else {
            // Redirigir al usuario al formulario de login
            return "redirect:/bienvenida?error=1";
        }
    }
    private String generarPalabraAleatoria() {
		
    	// Registra en los logs la entrada al método
    	logger.info("[INFORMACION]: Entrando en el método \"generarPalabraAleatoria\" en la clase \"ControladorCatalogo\"");
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
