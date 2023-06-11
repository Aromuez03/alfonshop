package AlfonShop.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import AlfonShop.dao.categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class productoDto {

	int id;
	String nombre;
	String descripcion;
	String urlImg;
	int precio;
	int cantidad;
	private categoria categoria;
	
	
	

    // Constructor con argumentos
    public productoDto(int id, String nombre, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }


    // Método de fábrica para deserialización
    @JsonCreator
    public static productoDto createFromJson(@JsonProperty("id") String id,
                                             @JsonProperty("nombre") String nombre,
                                             @JsonProperty("precio") String precio) {
        // Aquí puedes convertir los valores de tipo String a los tipos adecuados
        int parsedId = Integer.parseInt(id);
        int parsedPrecio = Integer.parseInt(precio);

        // Crear y devolver una nueva instancia de productoDto
        return new productoDto(parsedId, nombre, parsedPrecio);
    }
}
