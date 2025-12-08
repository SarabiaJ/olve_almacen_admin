package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDAO {

    private final JdbcTemplate jdbcTemplate;

    public ClienteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Cliente> rowMapper = (rs, rowNum) ->
            new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo")
            );

    // Listar todos los clientes
    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Obtener cliente por ID
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        List<Cliente> lista = jdbcTemplate.query(sql, rowMapper, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    // Agregar un cliente
    public boolean agregar(Cliente c) {
        String sql = "INSERT INTO cliente (nombre, telefono, correo) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                c.getNombre(),
                c.getTelefono(),
                c.getCorreo()) > 0;
    }

    // Actualizar un cliente
    public boolean actualizar(Cliente c) {
        String sql = "UPDATE cliente SET nombre = ?, telefono = ?, correo = ? WHERE id_cliente = ?";
        return jdbcTemplate.update(sql,
                c.getNombre(),
                c.getTelefono(),
                c.getCorreo(),
                c.getIdCliente()) > 0;
    }

    // Eliminar un cliente
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
