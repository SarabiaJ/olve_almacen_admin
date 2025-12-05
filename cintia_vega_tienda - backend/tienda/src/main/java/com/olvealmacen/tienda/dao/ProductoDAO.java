package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.modelo.Producto;
import com.olvealmacen.tienda.config.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT p.id, p.nombre, p.descripcion, p.imagen, p.precio, p.stock, " +
                     "c.id AS idCategoria, c.nombre AS nombreCategoria " +
                     "FROM productos p " +
                     "INNER JOIN categorias c ON p.id_categoria = c.id";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();

                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setImagen(rs.getString("imagen"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setNombreCategoria(rs.getString("nombreCategoria"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos(nombre, descripcion, imagen, precio, stock, id_categoria) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setString(3, p.getImagen());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getIdCategoria());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, imagen=?, precio=?, stock=?, id_categoria=? " +
                     "WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setString(3, p.getImagen());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getIdCategoria());
            ps.setInt(7, p.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
