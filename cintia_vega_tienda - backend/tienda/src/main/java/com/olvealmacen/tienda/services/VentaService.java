package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CompraDAO;
import com.olvealmacen.tienda.modelo.Compra;
import java.util.List;

public class CompraService {

    private CompraDAO compraDAO = new CompraDAO();

    // Obtener todas las compras
    public List<Compra> obtenerCompras() {
        return compraDAO.listar();
    }

    // Obtener compra por ID
    public Compra obtenerCompraPorId(int id) {
        return compraDAO.obtenerPorId(id);
    }

    // Agregar una compra
    public boolean agregarCompra(Compra compra) {
        return compraDAO.agregar(compra);
    }

    // Actualizar una compra
    public boolean actualizarCompra(Compra compra) {
        return compraDAO.actualizar(compra);
    }

    // Eliminar una compra
    public boolean eliminarCompra(int id) {
        return compraDAO.eliminar(id);
    }
}