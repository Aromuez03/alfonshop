package AlfonShop.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dwh_producto", schema = "dwh_alfonshop")
public class producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	int id;

	@Column(name = "nombre")
	String nombre;

	@Column(name = "descripcion")
	String descripcion;
	
	@Column(name = "imagen")
	String urlImg;

	@Column(name = "precio")
	int precio;

	@Column(name = "cantidad")
	int cantidad;

	@OneToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id_categoria")
	private categoria categoria;

}
