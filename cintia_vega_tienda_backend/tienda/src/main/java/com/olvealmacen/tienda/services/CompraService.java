package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CategoriaDAO;
import com.olvealmacen.tienda.dao.CompraDAO;
import com.olvealmacen.tienda.modelo.Compra;
import java.util.List;

public class CompraService {

    private final CompraDAO compraDAO;

    public CompraService(CompraDAO compraDAO) {
        this.compraDAO = compraDAO;
    }

    public List<Compra> obtenerCompras() {
        return compraDAO.listar();
    }

    public Compra obtenerCompraPorId(int id) {
        return compraDAO.obtenerPorId(id);
    }

    public boolean agregarCompra(Compra compra) {
        return compraDAO.agregar(compra);
    }

    public boolean actualizarCompra(Compra compra) {
        return compraDAO.actualizar(compra);
    }

    public boolean eliminarCompra(int id) {
        return compraDAO.eliminar(id);
    }
}
