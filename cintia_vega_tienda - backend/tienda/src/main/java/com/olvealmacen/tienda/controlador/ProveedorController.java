package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Proveedor;
import com.olvealmacen.tienda.services.ProveedorService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/api/proveedores")
public class ProveedorController extends HttpServlet {

    private ProveedorService service = new ProveedorService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String json = gson.toJson(service.listar());
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Proveedor p = gson.fromJson(req.getReader(), Proveedor.class);
        boolean ok = service.agregar(p);
        resp.getWriter().write("{\"success\":" + ok + "}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Proveedor p = gson.fromJson(req.getReader(), Proveedor.class);
        boolean ok = service.actualizar(p);
        resp.getWriter().write("{\"success\":" + ok + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean ok = service.eliminar(id);
        resp.getWriter().write("{\"success\":" + ok + "}");
    }
}
