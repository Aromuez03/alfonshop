package AlfonShop.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AlfonShop.dao.estado;
import AlfonShop.repositorio.estadoRepositorio;

@Service
public class estadoRepoImpl {

	@Autowired
    private estadoRepositorio repoEstados;
	public List<estado> buscarTodosLosEstados() {
        return (List<estado>) repoEstados.findAll();
    }

    public estado buscarEstadoPorId(int id) {
        return repoEstados.findById(id).orElse(null);
    }
}
