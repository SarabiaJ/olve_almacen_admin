package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Producto;
import com.olvealmacen.tienda.services.ProductoService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;
    private final Gson gson;

    public ProductoController(ProductoService service) {
        this.service = service;
        this.gson = new Gson();
    }

    @GetMapping
    public String listar() {
        return gson.toJson(service.obtenerTodos());
    }

    @PostMapping
    public String insertar(@RequestBody Producto p) {
        boolean ok = service.insertar(p);
        return "{\"success\": " + ok + "}";
    }

    @PutMapping
    public String actualizar(@RequestBody Producto p) {
        boolean ok = service.actualizar(p);
        return "{\"success\": " + ok + "}";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean ok = service.eliminar(id);
        return "{\"success\": " + ok + "}";
    }
}