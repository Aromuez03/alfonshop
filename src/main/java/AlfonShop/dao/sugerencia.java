package AlfonShop.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dwh_sugerencia", schema = "dwh_alfonshop")
public class sugerencia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sugerencia")
    int id;

    @Column(name = "mensaje")
    String mensaje;

    @OneToOne
	@JoinColumn(name = "estado_id", referencedColumnName = "id_estado")
    estado estado;

}
