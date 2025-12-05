package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.config.ConexionDB;
import com.olvealmacen.tienda.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // Obtener todas las categorías
    public List<Categoria> obtenerCategorias() {
        List<Categoria> lista = new ArrayList<>();

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM categoria");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Obtener una sola categoría
    public Categoria obtenerCategoriaPorId(int id) {
        Categoria c = null;

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM categoria WHERE id=?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    // Crear categoría
    public boolean agregarCategoria(Categoria categoria) {
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement("INSERT INTO categoria (id, nombre) VALUES (?, ?)")) {

            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setString(2, categoria.getNombreCategoria());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar categoría
    public boolean actualizarCategoria(Categoria categoria) {
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE categoria SET nombre=? WHERE id=?")) {

            stmt.setString(1, categoria.getNombreCategoria());
            stmt.setInt(2, categoria.getIdCategoria());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar categoría
    public boolean eliminarCategoria(int id) {
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM categoria WHERE id=?")) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
