package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Proveedor;
import com.olvealmacen.tienda.services.ProveedorService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService service;
    private final Gson gson;

    public ProveedorController(ProveedorService service) {
        this.service = service;
        this.gson = new Gson();
    }

    @GetMapping
    public String listar() {
        return gson.toJson(service.listar());
    }

    @PostMapping
    public String agregar(@RequestBody String body) {
        Proveedor p = gson.fromJson(body, Proveedor.class);
        boolean ok = service.agregar(p);
        return "{\"success\": " + ok + "}";
    }

    @PutMapping
    public String actualizar(@RequestBody String body) {
        Proveedor p = gson.fromJson(body, Proveedor.class);
        boolean ok = service.actualizar(p);
        return "{\"success\": " + ok + "}";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean ok = service.eliminar(id);
        return "{\"success\": " + ok + "}";
    }
}