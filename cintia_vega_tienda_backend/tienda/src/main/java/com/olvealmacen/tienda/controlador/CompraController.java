package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Compra;
import com.olvealmacen.tienda.services.CompraService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://sarabiaj.github.io")
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService service;
    private final Gson gson;

    public CompraController(CompraService service) {
        this.service = service;
        this.gson = new Gson();
    }

    @GetMapping
    public String listar() {
        List<Compra> lista = service.obtenerCompras();
        return gson.toJson(lista);
    }

    @PostMapping
    public String agregar(@RequestBody String body) {
        Compra c = gson.fromJson(body, Compra.class);
        boolean ok = service.agregarCompra(c);
        return "{\"success\": " + ok + "}";
    }

    @PutMapping
    public String actualizar(@RequestBody String body) {
        Compra c = gson.fromJson(body, Compra.class);
        boolean ok = service.actualizarCompra(c);
        return "{\"success\": " + ok + "}";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean ok = service.eliminarCompra(id);
        return "{\"success\": " + ok + "}";
    }
}