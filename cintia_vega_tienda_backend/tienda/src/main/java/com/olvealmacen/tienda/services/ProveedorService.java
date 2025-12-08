package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CategoriaDAO;
import com.olvealmacen.tienda.dao.ProveedorDAO;
import com.olvealmacen.tienda.modelo.Proveedor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProveedorService {

    private final ProveedorDAO proveedorDAO;

    @Autowired
    public ProveedorService(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    public List<Proveedor> listar() {
        return proveedorDAO.listar();
    }

    public boolean agregar(Proveedor p) {
        return proveedorDAO.agregar(p);
    }

    public boolean actualizar(Proveedor p) {
        return proveedorDAO.actualizar(p);
    }

    public boolean eliminar(int id) {
        return proveedorDAO.eliminar(id);
    }
}
