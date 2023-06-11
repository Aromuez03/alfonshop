package AlfonShop.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import AlfonShop.dao.categoria;
import AlfonShop.dao.producto;

public interface productoRepositorio extends CrudRepository<producto, Integer> {

	List<producto> findByCategoria(categoria categoria);
}
