package AlfonShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import AlfonShop.dao.estado;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class sugerenciaDto {

	 	int id;
	    String mensaje;
	    estado estado;
}
