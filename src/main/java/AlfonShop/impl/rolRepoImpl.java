package AlfonShop.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AlfonShop.dao.rol;
import AlfonShop.repositorio.rolRepositorio;

@Service
public class rolRepoImpl {

	@Autowired
    private rolRepositorio repoRoles;

    public List<rol> buscarTodosLosRoles() {
        return (List<rol>) repoRoles.findAll();
    }

    public rol buscarRolPorId(int id) {
        return repoRoles.findById(id).orElse(null);
    }
}
