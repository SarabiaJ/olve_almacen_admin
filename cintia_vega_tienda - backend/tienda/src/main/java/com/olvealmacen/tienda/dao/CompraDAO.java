package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Compra;
import com.olvealmacen.tienda.config.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    private Connection con;

    public CompraDAO() {
        con = ConexionDB.getConnection();
    }

    // Listar todas las compras
    public List<Compra> listar() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM compras";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Compra c = new Compra(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getInt("idProveedor"),
                        rs.getDouble("total")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Obtener compra por ID
    public Compra obtenerPorId(int id) {
        String sql = "SELECT * FROM compras WHERE id=?";
        Compra compra = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                compra = new Compra(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getInt("idProveedor"),
                        rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compra;
    }

    // Agregar una compra
    public boolean agregar(Compra c) {
        String sql = "INSERT INTO compras (fecha, idProveedor, total) VALUES (?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getFecha());
            ps.setInt(2, c.getIdProveedor());
            ps.setDouble(3, c.getTotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar una compra
    public boolean actualizar(Compra c) {
        String sql = "UPDATE compras SET fecha=?, idProveedor=?, total=? WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getFecha());
            ps.setInt(2, c.getIdProveedor());
            ps.setDouble(3, c.getTotal());
            ps.setInt(4, c.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar una compra
    public boolean eliminar(int id) {
        String sql = "DELETE FROM compras WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}