package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Compra;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompraDAO {

    private final JdbcTemplate jdbcTemplate;

    public CompraDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Compra> rowMapper = (rs, rowNum) ->
            new Compra(
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("idProveedor"),
                    rs.getDouble("total")
            );

    // Listar todas las compras
    public List<Compra> listar() {
        String sql = "SELECT * FROM compra";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Obtener compra por ID
    public Compra obtenerPorId(int id) {
        String sql = "SELECT * FROM compra WHERE id=?";
        List<Compra> lista = jdbcTemplate.query(sql, rowMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    // Agregar una compra
    public boolean agregar(Compra c) {
        String sql = "INSERT INTO compra (fecha, idProveedor, total) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, c.getFecha(), c.getIdProveedor(), c.getTotal()) > 0;
    }

    // Actualizar una compra
    public boolean actualizar(Compra c) {
        String sql = "UPDATE compra SET fecha=?, idProveedor=?, total=? WHERE id=?";
        return jdbcTemplate.update(sql, c.getFecha(), c.getIdProveedor(), c.getTotal(), c.getId()) > 0;
    }

    // Eliminar una compra
    public boolean eliminar(int id) {
        String sql = "DELETE FROM compra WHERE id=?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}