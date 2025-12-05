package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Producto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProductoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Producto> rowMapper = (rs, rowNum) -> {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setIdCategoria(rs.getInt("idCategoria"));
        p.setNombreCategoria(rs.getString("nombreCategoria"));
        return p;
    };

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        String sql = "SELECT p.id, p.nombre, p.descripcion, p.imagen, p.precio, p.stock, " +
                     "c.id AS idCategoria, c.nombre AS nombreCategoria " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.id_categoria = c.id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Insertar un producto
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos(nombre, descripcion, imagen, precio, stock, id_categoria) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                p.getNombre(),
                p.getDescripcion(),
                p.getImagen(),
                p.getPrecio(),
                p.getStock(),
                p.getIdCategoria()) > 0;
    }

    // Actualizar un producto
    public boolean actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, imagen=?, precio=?, stock=?, id_categoria=? " +
                     "WHERE id=?";
        return jdbcTemplate.update(sql,
                p.getNombre(),
                p.getDescripcion(),
                p.getImagen(),
                p.getPrecio(),
                p.getStock(),
                p.getIdCategoria(),
                p.getId()) > 0;
    }

    // Eliminar un producto
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id=?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}