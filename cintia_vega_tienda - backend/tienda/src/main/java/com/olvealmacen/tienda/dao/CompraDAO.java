package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Compra;
import com.olvealmacen.tienda.config.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    Connection con;

    public CompraDAO() {
        con = ConexionDB.conectar();
    }

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean agregar(Compra c) {
        String sql = "INSERT INTO compras (fecha, idProveedor, total) VALUES (?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getFecha());
            ps.setInt(2, c.getIdProveedor());
            ps.setDouble(3, c.getTotal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Compra c) {
        String sql = "UPDATE compras SET fecha=?, idProveedor=?, total=? WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getFecha());
            ps.setInt(2, c.getIdProveedor());
            ps.setDouble(3, c.getTotal());
            ps.setInt(4, c.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM compras WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
