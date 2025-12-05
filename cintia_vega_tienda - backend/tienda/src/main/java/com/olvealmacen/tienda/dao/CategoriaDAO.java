package com.olvealmacen.tienda.dao;

import com.olvealmacen.tienda.config.ConexionDB;
import com.olvealmacen.tienda.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    ConexionDB cn = new ConexionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // LISTAR TODO
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setNombreCategoria(rs.getString("nombreCategoria"));
                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("ERROR LISTAR: " + e.getMessage());
        }

        return lista;
    }
}
