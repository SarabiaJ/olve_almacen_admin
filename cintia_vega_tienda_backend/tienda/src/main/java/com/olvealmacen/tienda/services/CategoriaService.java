package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CategoriaDAO;
import com.olvealmacen.tienda.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // <-- IMPORTANTE: le dice a Spring que esta clase es un servicio
public class CategoriaService {

    private final CategoriaDAO categoriaDAO;

    @Autowired  // <-- Spring inyecta automaticamente el DAO
    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    // Obtener todas las categorías
    public List<Categoria> obtenerCategorias() {
        return categoriaDAO.obtenerCategorias();
    }

    // Obtener categoría por ID
    public Categoria obtenerPorId(int id) {
        return categoriaDAO.obtenerCategoriaPorId(id);
    }

    // Agregar categoría
    public boolean agregarCategoria(Categoria categoria) {
        return categoriaDAO.agregarCategoria(categoria);
    }

    // Actualizar categoría
    public boolean actualizarCategoria(Categoria categoria) {
        return categoriaDAO.actualizarCategoria(categoria);
    }

    // Eliminar categoría
    public boolean eliminarCategoria(int id) {
        return categoriaDAO.eliminarCategoria(id);
    }
}