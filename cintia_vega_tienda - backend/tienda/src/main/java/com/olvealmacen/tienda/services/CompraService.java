package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CompraDAO;
import com.olvealmacen.tienda.modelo.Compra;
import java.util.List;

public class CompraService {

    private CompraDAO compraDAO = new CompraDAO();

    public List<Compra> obtenerCompras() {
        return compraDAO.obtenerCompras();
    }

    public Compra obtenerCompraPorId(int id) {
        return compraDAO.obtenerCompraPorId(id);
    }

    public boolean agregarCompra(Compra compra) {
        return compraDAO.agregarCompra(compra);
    }

    public boolean actualizarCompra(Compra compra) {
        return compraDAO.actualizarCompra(compra);
    }

    public boolean eliminarCompra(int id) {
        return compraDAO.eliminarCompra(id);
    }
}
