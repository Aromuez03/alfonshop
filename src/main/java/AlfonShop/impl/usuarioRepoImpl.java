package AlfonShop.impl;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AlfonShop.dto.ToDAO;
import AlfonShop.dto.ToDTO;
import AlfonShop.dto.usuarioDto;
import AlfonShop.repositorio.usuarioRepositorio;

@Service
public class usuarioRepoImpl {
    
    @Autowired
    private usuarioRepositorio repoUsuario;
    
    @Autowired
    private ToDTO toDto;
    
    @Autowired
    private ToDAO toDao;
    
    /**
     * Busca un usuario por su ID en el repositorio de usuarios.
     * @param id El ID del usuario a buscar.
     * @return El usuario con el ID especificado, o null si no se encontró ningún usuario.
     */
    public usuarioDto buscarUsuarioPorId(int id) {
        return toDto.ToDTOusu(repoUsuario.findById(id).orElse(null));
    }
    
    /**
     * Busca un usuario por su correo electrónico en el repositorio de usuarios.
     * @param email El correo electrónico del usuario a buscar.
     * @return El usuario con el correo electrónico especificado, o null si no se encontró ningún usuario.
     */
    public usuarioDto buscarPorEmail(String email) {
        return toDto.ToDTOusu(repoUsuario.findByEmail(email).orElse(null));
    }
    
    /**
     * Obtiene todos los usuarios almacenados en el repositorio de usuarios.
     * @return Una lista de todos los usuarios almacenados en el repositorio.
     */
    public List<usuarioDto> buscarTodosLosUsuarios() {
        // Convertir Iterable a Stream utilizando Spliterator
        List<usuarioDto> usuariosDto = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(repoUsuario.findAll().iterator(), Spliterator.ORDERED),
                false)
                .map(toDto::ToDTOusu) // Mapear cada usuario DAO a usuario DTO
                .collect(Collectors.toList()); // Recolectar los usuarios DTO en una lista
        return usuariosDto;
    }

    /**
     * Actualiza un usuario en el repositorio de usuarios.
     * @param usuarioDto El usuario DTO a actualizar.
     */
    public void actualizarUsuario(usuarioDto usuarioDto) {
        repoUsuario.save(toDao.ToDAOusu(usuarioDto)); // Convertir usuario DTO a usuario DAO y guardar en el repositorio
    }
    
    /**
     * Elimina un usuario del repositorio de usuarios.
     * @param id El ID del usuario a eliminar.
     */
    public void eliminarUsuario(int id) {
        repoUsuario.deleteById(id);
    }
}
