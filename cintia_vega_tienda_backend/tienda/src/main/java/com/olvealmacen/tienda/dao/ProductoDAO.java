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
        p.setId(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen_url"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock_actual"));
        p.setIdCategoria(rs.getInt("id_categoria"));
        return p;
    };

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.imagen_url, p.precio, p.stock_actual, " +
                     "c.id_categoria AS id_categoria " +
                     "FROM producto p " +
                     "INNER JOIN categoria c ON p.id_categoria = c.id_categoria";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Insertar un producto
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO producto (nombre, descripcion, imagen_url, precio, stock_actual, id_categoria) " +
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
        String sql = "UPDATE producto SET nombre=?, descripcion=?, imagen_url=?, precio=?, stock_actual=?, id_categoria=? " +
                 "WHERE id_producto = ?";
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
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}