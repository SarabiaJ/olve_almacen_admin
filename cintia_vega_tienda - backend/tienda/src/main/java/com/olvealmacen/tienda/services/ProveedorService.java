package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.ProveedorDAO;
import com.olvealmacen.tienda.modelo.Proveedor;

import java.util.List;

public class ProveedorService {

    private ProveedorDAO dao = new ProveedorDAO();

    public List<Proveedor> listar() {
        return dao.listar();
    }

    public boolean agregar(Proveedor p) {
        return dao.agregar(p);
    }

    public boolean actualizar(Proveedor p) {
        return dao.actualizar(p);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
