package AlfonShop.impl;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AlfonShop.dao.sugerencia;
import AlfonShop.dto.ToDAO;
import AlfonShop.dto.ToDTO;
import AlfonShop.dto.sugerenciaDto;
import AlfonShop.repositorio.sugerenciaRepositorio;

@Service
public class sugerenciaRepoImpl {


	@Autowired
    private sugerenciaRepositorio repoSugerencia;

    @Autowired
    private ToDTO toDTO;
    
    @Autowired
    private ToDAO toDAO;

    public List<sugerenciaDto> buscarTodasLasSugerencias() {
        Iterable<sugerencia> sugerencias = repoSugerencia.findAll();
        List<sugerenciaDto> sugerenciasDTO = new ArrayList<>();
        for (sugerencia sugerencia : sugerencias) {
            sugerenciasDTO.add(toDTO.ToDTOsug(sugerencia));
        }
        return sugerenciasDTO;
    }


    public sugerenciaDto buscarSugerenciaPorId(int id) {
        sugerencia sugerencia = repoSugerencia.findById(id).orElse(null);
        return toDTO.ToDTOsug(sugerencia);
    }

    public void guardarSugerencia(sugerenciaDto sugerenciaDto) {
        sugerencia sugerencia = toDAO.ToDAOsug(sugerenciaDto);
        repoSugerencia.save(sugerencia);
    }

    public void eliminarSugerencia(int id) {
        repoSugerencia.deleteById(id);
    }
}
