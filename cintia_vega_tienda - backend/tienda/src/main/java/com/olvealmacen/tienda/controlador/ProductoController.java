package com.olvealmacen.tienda.controlador;

import com.google.gson.Gson;
import com.olvealmacen.tienda.modelo.Producto;
import com.olvealmacen.tienda.services.ProductoService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/productos")
public class ProductoController extends HttpServlet {

    private ProductoService service = new ProductoService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        String json = gson.toJson(service.obtenerTodos());

        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Producto p = new Producto();

        p.setNombre(request.getParameter("nombre"));
        p.setDescripcion(request.getParameter("descripcion"));
        p.setImagen(request.getParameter("imagen"));
        p.setPrecio(Double.parseDouble(request.getParameter("precio")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));

        boolean ok = service.insertar(p);
        response.getWriter().write("{\"success\": " + ok + "}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Producto p = new Producto();

        p.setId(Integer.parseInt(request.getParameter("id")));
        p.setNombre(request.getParameter("nombre"));
        p.setDescripcion(request.getParameter("descripcion"));
        p.setImagen(request.getParameter("imagen"));
        p.setPrecio(Double.parseDouble(request.getParameter("precio")));
        p.setStock(Integer.parseInt(request.getParameter("stock")));
        p.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));

        boolean ok = service.actualizar(p);
        response.getWriter().write("{\"success\": " + ok + "}");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        boolean ok = service.eliminar(id);
        response.getWriter().write("{\"success\": " + ok + "}");
    }
}

