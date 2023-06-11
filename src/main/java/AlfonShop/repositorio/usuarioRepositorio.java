package AlfonShop.repositorio;

import org.springframework.data.repository.CrudRepository;

import AlfonShop.dao.usuario;

public interface usuarioRepositorio extends CrudRepository<usuario, Integer> {

	public usuario findByEmail(String email);
}
