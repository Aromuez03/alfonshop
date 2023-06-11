package AlfonShop.dto;

import org.springframework.stereotype.Component;

import AlfonShop.dao.producto;
import AlfonShop.dao.sugerencia;
import AlfonShop.dao.usuario;

@Component
public class ToDTO {

	public usuarioDto ToDTOusu(usuario usu) {
		usuarioDto usuDTO = new usuarioDto();
		usuDTO.setId(usu.getId());
		usuDTO.setNombre(usu.getNombre());
		usuDTO.setClave(usu.getClave());
		usuDTO.setEmail(usu.getEmail());
		usuDTO.setTelefono(usu.getTelefono());
		usuDTO.setRol(usu.getRol());
		usuDTO.setVerificado(usu.getVerificado());
		return usuDTO;
	}
	public sugerenciaDto ToDTOsug(sugerencia sugerencia) {
		sugerenciaDto sugerenciaDTO = new sugerenciaDto();
		sugerenciaDTO.setId(sugerencia.getId());
		sugerenciaDTO.setMensaje(sugerencia.getMensaje());
		sugerenciaDTO.setEstado(sugerencia.getEstado());
		return sugerenciaDTO;
	}
	public productoDto ToDTOprod(producto producto) {
		productoDto productoDto = new productoDto();
		productoDto.setId(producto.getId());
		productoDto.setNombre(producto.getNombre());
		productoDto.setDescripcion(producto.getDescripcion());
		productoDto.setCategoria(producto.getCategoria());
		productoDto.setPrecio(producto.getPrecio());
		productoDto.setCantidad(producto.getCantidad());
		productoDto.setUrlImg(producto.getUrlImg());
		return productoDto;
	}
	
	
}
