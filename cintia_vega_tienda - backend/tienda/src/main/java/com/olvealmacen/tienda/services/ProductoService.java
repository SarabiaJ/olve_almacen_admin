package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.ProductoDAO;
import com.olvealmacen.tienda.modelo.Producto;

import java.util.List;

public class ProductoService {

    private ProductoDAO dao = new ProductoDAO();

    public List<Producto> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public boolean insertar(Producto p) {
        return dao.insertar(p);
    }

    public boolean actualizar(Producto p) {
        return dao.actualizar(p);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
