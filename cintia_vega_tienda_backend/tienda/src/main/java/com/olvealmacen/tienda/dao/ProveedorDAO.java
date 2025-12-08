package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Proveedor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProveedorDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProveedorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Proveedor> rowMapper = (rs, rowNum) ->
            new Proveedor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
            );

    // Listar todos los proveedores
    public List<Proveedor> listar() {
        String sql = "SELECT * FROM proveedor";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Agregar un proveedor
    public boolean agregar(Proveedor p) {
        String sql = "INSERT INTO proveedor (nombre, telefono, direccion) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                p.getNombre(),
                p.getTelefono(),
                p.getDireccion()) > 0;
    }

    // Actualizar un proveedor
    public boolean actualizar(Proveedor p) {
        String sql = "UPDATE proveedor SET nombre=?, telefono=?, direccion=? WHERE id=?";
        return jdbcTemplate.update(sql,
                p.getNombre(),
                p.getTelefono(),
                p.getDireccion(),
                p.getId()) > 0;
    }

    // Eliminar un proveedor
    public boolean eliminar(int id) {
        String sql = "DELETE FROM proveedor WHERE id=?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}