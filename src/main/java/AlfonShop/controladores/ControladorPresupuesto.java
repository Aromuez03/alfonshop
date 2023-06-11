package AlfonShop.controladores;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import AlfonShop.dto.productoDto;
import AlfonShop.dto.usuarioDto;
import AlfonShop.impl.productoRepoImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ControladorPresupuesto {

	// Crear una instancia de Logger para la clase ControladorPresupuesto
	private static final Logger logger = LoggerFactory.getLogger(ControladorPresupuesto.class);
	
	@Autowired
	private productoRepoImpl productoService;
	
	@GetMapping("/presupuesto")
	public String mostrarPresupuesto(Model model, HttpServletRequest request) {
		
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"mostrarPresupuesto\" en la clase \"ControladorPresupuesto\"");
		
	    // Obtener el usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si el usuario tiene el rol y está verificado
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    if ((rolUsuario.equals("usuario") || rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        // Aquí puedes cargar los productos disponibles y enviarlos a la vista
	        List<productoDto> productosDisponibles = productoService.obtenerTodosLosProductos();
	        model.addAttribute("productos", productosDisponibles);
            model.addAttribute("rolUsuario", rolUsuario);
	        return "presupuesto";
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}

	@PostMapping("/presupuesto/generar")
	@ResponseBody
	public String generarPresupuesto(@RequestBody List<Integer> productosIds, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		// Registra en los logs la entrada al método
		logger.info("[INFORMACION]: Entrando en el método \"generarPresupuesto\" en la clase \"ControladorPresupuesto\"");
		
	    // Obtener el usuario desde la sesión
	    usuarioDto user = (usuarioDto) request.getSession().getAttribute("usuarioLogeado");

	    // Si el usuario no está autenticado o la sesión no contiene un usuario válido, redirigir al formulario de login
	    if (user == null || user.getRol() == null) {
	        return "redirect:/Login?error2=1";
	    }

	    // Comprobar si el usuario tiene el rol y está verificado
	    String rolUsuario = user.getRol().getNombre();
	    boolean verificado = user.getVerificado();
	    if ((rolUsuario.equals("usuario") || rolUsuario.equals("admin") || rolUsuario.equals("superadmin")) && verificado) {
	        List<productoDto> productos = new ArrayList<>();
	        for (Integer id : productosIds) {
	            productoDto producto = productoService.obtenerProductoPorId(id);
	            if (producto != null) {
	                productos.add(producto);
	            }
	        }

	        // Crear un documento PDF y escribir en él los productos seleccionados
	        Document document = new Document();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try {
	            PdfWriter.getInstance(document, baos);
	            document.open();

	            // Encabezado personalizado
	            document.add(new Paragraph("AlfonShop - Presupuesto\n\n"));

	            // Detalles de los productos
	            document.add(new Paragraph("Nombre - Precio - Cantidad"));

	            int total = 0; // Variable para calcular el total de los precios

	            for (productoDto producto : productos) {
	                String contenido = producto.getNombre() + " - $" + producto.getPrecio() + " - 1";
	                document.add(new Paragraph(contenido));

	                // Calcular el total sumando los precios de los productos
	                total += producto.getPrecio();
	            }

	            // Mostrar el total
	            document.add(new Paragraph("\nTotal: " + total + "€"));
	        } catch (DocumentException e) {
	            System.out.println("[Error]: "+e);
	        } finally {
	            document.close();
	        }

	        // Configurar la respuesta HTTP para descargar el PDF generado
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=Presupuesto.pdf");
	        response.setContentLength(baos.size());

	        // Enviar el contenido del PDF al cliente
	        baos.writeTo(response.getOutputStream());
	        response.getOutputStream().flush();
	        return "redirect:/bienvenida";
	    } else {
	        // Redirigir al usuario al formulario de login
	        return "redirect:/bienvenida?error=1";
	    }
	}


 

}
