package com.olvealmacen.tienda.controlador;

import com.olvealmacen.tienda.services.CategoriaService;
import com.olvealmacen.tienda.modelo.Categoria;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://sarabiaj.github.io")
@RestController
@RequestMapping("/api/categoria")
public class CategoriaControlador {

    private final CategoriaService categoriaService;
    private final Gson gson;

    public CategoriaControlador(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
        this.gson = new Gson();
    }

    @GetMapping({"", "/listar"})
    public String listarCategorias() {
        List<Categoria> lista = categoriaService.obtenerCategorias();
        return gson.toJson(lista);
    }
}
