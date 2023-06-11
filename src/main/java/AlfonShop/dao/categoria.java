package AlfonShop.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dwh_categoria", schema = "dwh_alfonshop")
public class categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    int id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "descripcion")
    String descripcion;
}
