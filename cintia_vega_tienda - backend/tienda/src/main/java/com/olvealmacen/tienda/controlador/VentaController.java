package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Venta;
import com.olvealmacen.tienda.services.VentaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/ventas")
public class VentaController extends HttpServlet {

    private final VentaService service = new VentaService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write(gson.toJson(service.listar()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Venta venta = gson.fromJson(req.getReader(), Venta.class);
        boolean ok = service.agregar(venta);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write("{\"success\": " + ok + "}");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Venta venta = gson.fromJson(req.getReader(), Venta.class);
        boolean ok = service.actualizar(venta);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write("{\"success\": " + ok + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        boolean ok = service.eliminar(id);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write("{\"success\": " + ok + "}");
    }
}