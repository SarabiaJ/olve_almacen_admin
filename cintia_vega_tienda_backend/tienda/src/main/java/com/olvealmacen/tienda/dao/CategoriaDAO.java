package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Categoria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaDAO {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Categoria> rowMapper = (rs, rowNum) ->
            new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria")
            );

    // Obtener todas las categorías
    public List<Categoria> obtenerCategorias() {
        String sql = "SELECT * FROM categoria";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Obtener una sola categoría
    public Categoria obtenerCategoriaPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        List<Categoria> lista = jdbcTemplate.query(sql, rowMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    // Crear categoría
    public boolean agregarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                categoria.getIdCategoria(),
                categoria.getNombreCategoria()) > 0;
    }

    // Actualizar categoría
    public boolean actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre_categoria = ? WHERE id_categoria = ?";
        return jdbcTemplate.update(sql,
                categoria.getNombreCategoria(),
                categoria.getIdCategoria()) > 0;
    }

    // Eliminar categoría
    public boolean eliminarCategoria(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}