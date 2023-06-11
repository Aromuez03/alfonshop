package AlfonShop.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dlk_rol_usuario", schema = "dlk_informacional")
public class rol {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "descripcion")
    String descripcion;
}
