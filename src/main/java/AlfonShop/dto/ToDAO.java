package AlfonShop.dto;

import org.springframework.stereotype.Component;

import AlfonShop.dao.producto;
import AlfonShop.dao.sugerencia;
import AlfonShop.dao.usuario;

@Component
public class ToDAO {

	public usuario ToDAOusu(usuarioDto usuDTO) {
	    usuario usu = new usuario();
	    usu.setId(usuDTO.getId());
	    usu.setNombre(usuDTO.getNombre());
	    usu.setClave(usuDTO.getClave());
	    usu.setEmail(usuDTO.getEmail());
	    usu.setTelefono(usuDTO.getTelefono());
	    usu.setRol(usuDTO.getRol());
	    usu.setVerificado(usuDTO.getVerificado());
	    return usu;
	}
	public sugerencia ToDAOsug(sugerenciaDto sugerenciaDto) {
        sugerencia sug = new sugerencia();
        sug.setId(sugerenciaDto.getId());
        sug.setMensaje(sugerenciaDto.getMensaje());
        sug.setEstado(sugerenciaDto.getEstado());
        return sug;
    }
    
    public producto ToDAOprod(productoDto productoDto) {
        producto prod = new producto();
        prod.setId(productoDto.getId());
        prod.setNombre(productoDto.getNombre());
        prod.setDescripcion(productoDto.getDescripcion());
        prod.setCategoria(productoDto.getCategoria());
        prod.setPrecio(productoDto.getPrecio());
        prod.setCantidad(productoDto.getCantidad());
        prod.setUrlImg(productoDto.getUrlImg());
        return prod;
    }
    

}
