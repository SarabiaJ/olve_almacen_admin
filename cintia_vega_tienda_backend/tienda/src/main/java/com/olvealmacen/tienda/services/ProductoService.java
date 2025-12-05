package com.olvealmacen.tienda.services;

import com.olvealmacen.tienda.dao.CategoriaDAO;
import com.olvealmacen.tienda.dao.ProductoDAO;
import com.olvealmacen.tienda.modelo.Producto;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoDAO productoDAO;

    @Autowired
    public ProductoService(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public List<Producto> obtenerTodos() {
        return productoDAO.obtenerTodos();
    }

    public boolean insertar(Producto p) {
        return productoDAO.insertar(p);
    }

    public boolean actualizar(Producto p) {
        return productoDAO.actualizar(p);
    }

    public boolean eliminar(int id) {
        return productoDAO.eliminar(id);
    }
}
