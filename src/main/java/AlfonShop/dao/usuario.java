package AlfonShop.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dlk_usuario", schema = "dlk_informacional")
public class usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    int id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "clave")
    String clave;

    @Column(name = "email")
    String email;
    
    @Column(name = "telefono")
    String telefono;
    
	
    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private rol rol;
    
    @Column(name = "verificado")
    private Boolean verificado;
    

	public usuario orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
