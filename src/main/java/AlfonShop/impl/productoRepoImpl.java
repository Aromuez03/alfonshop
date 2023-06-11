package AlfonShop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import AlfonShop.dao.producto;
import AlfonShop.dto.ToDAO;
import AlfonShop.dto.ToDTO;
import AlfonShop.dto.productoDto;
import AlfonShop.repositorio.productoRepositorio;

@Service
public class productoRepoImpl {

    @Autowired
    private productoRepositorio repoProductos;

    @Autowired
    private ToDTO toDTO;

    @Autowired
    private ToDAO toDAO;

    public List<productoDto> obtenerTodosLosProductos() {
        Iterable<producto> productos = repoProductos.findAll();
        List<productoDto> productoDtoList = new ArrayList<>();
        for (producto prod : productos) {
            productoDtoList.add(toDTO.ToDTOprod(prod));
        }
        return productoDtoList;
    }

    public productoDto obtenerProductoPorId(int id) {
        producto producto = repoProductos.findById(id).orElse(null);
        return toDTO.ToDTOprod(producto);
    }

    public void guardarProducto(productoDto productoDto) {
        producto producto = toDAO.ToDAOprod(productoDto);
        repoProductos.save(producto);
    }

    public void eliminarProducto(int id) {
        repoProductos.deleteById(id);
    }
}
