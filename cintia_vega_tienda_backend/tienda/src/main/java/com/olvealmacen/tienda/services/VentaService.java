package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CategoriaDAO;
import com.olvealmacen.tienda.dao.VentaDAO;
import com.olvealmacen.tienda.modelo.Venta;

import java.util.List;

public class VentaService {

    private final VentaDAO ventaDAO;

    public VentaService(VentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    // Obtener todas las ventas
    public List<Venta> obtenerVentas() {
        return ventaDAO.listar();
    }

    // Obtener venta por ID
    public Venta obtenerVentaPorId(int id) {
        return ventaDAO.obtenerPorId(id);
    }

    // Agregar venta
    public boolean agregarVenta(Venta venta) {
        return ventaDAO.agregar(venta);
    }

    // Actualizar venta
    public boolean actualizarVenta(Venta venta) {
        return ventaDAO.actualizar(venta);
    }

    // Eliminar venta
    public boolean eliminarVenta(int id) {
        return ventaDAO.eliminar(id);
    }
}