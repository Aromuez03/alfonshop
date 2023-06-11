package AlfonShop.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AlfonShop.dao.categoria;
import AlfonShop.repositorio.categoriaRepositorio;

@Service
public class categoriaRepoImpl {
	@Autowired
    private categoriaRepositorio repoCategoria;
	
    public List<categoria> listarCategorias() {
        return (List<categoria>) repoCategoria.findAll();
    }

    public categoria obtenerCategoriaPorId(int id) {
        return repoCategoria.findById(id).orElse(null);
    }

    public void guardarCategoria(categoria categoria) {
    	repoCategoria.save(categoria);
    }

    public void eliminarCategoria(int id) {
    	repoCategoria.deleteById(id);
    }

}
