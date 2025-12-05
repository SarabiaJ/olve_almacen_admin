package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Venta;
import com.olvealmacen.tienda.config.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();

        String sql = "SELECT v.id, v.fecha, v.id_cliente, c.nombre AS nombre_cliente, " +
                     "v.metodo_pago, v.total " +
                     "FROM ventas v INNER JOIN clientes c ON v.id_cliente = c.id";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setFecha(rs.getString("fecha"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setNombreCliente(rs.getString("nombre_cliente"));
                v.setMetodoPago(rs.getString("metodo_pago"));
                v.setTotal(rs.getDouble("total"));
                lista.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean agregar(Venta v) {
        String sql = "INSERT INTO ventas (fecha, id_cliente, metodo_pago, total) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getFecha());
            ps.setInt(2, v.getIdCliente());
            ps.setString(3, v.getMetodoPago());
            ps.setDouble(4, v.getTotal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(Venta v) {
        String sql = "UPDATE ventas SET fecha=?, id_cliente=?, metodo_pago=?, total=? WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getFecha());
            ps.setInt(2, v.getIdCliente());
            ps.setString(3, v.getMetodoPago());
            ps.setDouble(4, v.getTotal());
            ps.setInt(5, v.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM ventas WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Venta obtenerPorId(int id) {
        String sql = "SELECT * FROM ventas WHERE id=?";
        Venta venta = null;

        try (Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venta = new Venta(
                    rs.getInt("id"),
                    rs.getString("fecha"),
                    rs.getInt("idCliente"),
                    rs.getString("nombreCliente"),
                    rs.getString("metodoPago"),
                    rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venta;
    }

}
