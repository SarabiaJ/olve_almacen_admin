package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Cliente;
import com.olvealmacen.tienda.services.ClienteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://sarabiaj.github.io")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;
    private final Gson gson;

    public ClienteController(ClienteService service) {
        this.service = service;
        this.gson = new Gson();
    }

    @GetMapping
    public String listar() {
        List<Cliente> lista = service.obtenerClientes();
        return gson.toJson(lista);
    }

    @PostMapping
    public String agregar(@RequestBody String body) {
        Cliente c = gson.fromJson(body, Cliente.class);
        boolean ok = service.agregarCliente(c);
        return "{\"success\": " + ok + "}";
    }

    @PutMapping
    public String actualizar(@RequestBody String body) {
        Cliente c = gson.fromJson(body, Cliente.class);
        boolean ok = service.actualizarCliente(c);
        return "{\"success\": " + ok + "}";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        boolean ok = service.eliminarCliente(id);
        return "{\"success\": " + ok + "}";
    }
}
