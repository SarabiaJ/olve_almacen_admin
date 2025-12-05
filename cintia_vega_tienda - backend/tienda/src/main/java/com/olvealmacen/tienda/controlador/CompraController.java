package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Compra;
import com.olvealmacen.tienda.services.CompraService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/compras")
public class CompraController extends HttpServlet {

    private CompraService service = new CompraService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Compra> lista = service.listar();
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(lista));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        Compra c = gson.fromJson(reader, Compra.class);

        boolean ok = service.agregar(c);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": " + ok + "}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        Compra c = gson.fromJson(reader, Compra.class);

        boolean ok = service.actualizar(c);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": " + ok + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean ok = service.eliminar(id);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": " + ok + "}");
    }
}
