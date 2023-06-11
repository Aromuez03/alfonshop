package AlfonShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import AlfonShop.dao.rol;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class usuarioDto {

    int id;
    String nombre;
    String clave;
    String email;
    String telefono;
    rol rol;
    Boolean verificado;
}
