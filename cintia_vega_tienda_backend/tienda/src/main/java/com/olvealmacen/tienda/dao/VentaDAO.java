package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Venta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VentaDAO {

    private final JdbcTemplate jdbcTemplate;

    public VentaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Venta> rowMapper = (rs, rowNum) ->
            new Venta(
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("id_cliente"),
                    rs.getString("nombre_cliente"),
                    rs.getString("metodo_pago"),
                    rs.getDouble("total")
            );

    // Listar todas las ventas
    public List<Venta> listar() {
        String sql = "SELECT v.id, v.fecha, v.id_cliente, c.nombre AS nombre_cliente, " +
                     "v.metodo_pago, v.total " +
                     "FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Agregar una venta
    public boolean agregar(Venta v) {
        String sql = "INSERT INTO ventas (fecha, id_cliente, metodo_pago, total) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                v.getFecha(),
                v.getIdCliente(),
                v.getMetodoPago(),
                v.getTotal()) > 0;
    }

    // Actualizar una venta
    public boolean actualizar(Venta v) {
        String sql = "UPDATE ventas SET fecha=?, id_cliente=?, metodo_pago=?, total=? WHERE id=?";
        return jdbcTemplate.update(sql,
                v.getFecha(),
                v.getIdCliente(),
                v.getMetodoPago(),
                v.getTotal(),
                v.getId()) > 0;
    }

    // Eliminar una venta
    public boolean eliminar(int id) {
        String sql = "DELETE FROM ventas WHERE id=?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    // Obtener venta por ID
    public Venta obtenerPorId(int id) {
        String sql = "SELECT v.id, v.fecha, v.id_cliente, c.nombre AS nombre_cliente, v.metodo_pago, v.total " +
                     "FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id WHERE v.id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    }
}