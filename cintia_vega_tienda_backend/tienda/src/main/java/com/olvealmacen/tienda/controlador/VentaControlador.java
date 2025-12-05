package com.olvealmacen.tienda.controlador;

import com.olvealmacen.tienda.modelo.Venta;
import com.olvealmacen.tienda.services.VentaService;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ventas")
public class VentaControlador extends HttpServlet {

    private VentaService ventaService = new VentaService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Venta> listaVentas = ventaService.obtenerVentas();
        String json = gson.toJson(listaVentas);

        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Suponiendo que recibes un JSON con la venta
        Venta venta = gson.fromJson(request.getReader(), Venta.class);
        boolean resultado = ventaService.agregarVenta(venta);

        out.print("{\"success\":" + resultado + "}");
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Venta venta = gson.fromJson(request.getReader(), Venta.class);
        boolean resultado = ventaService.actualizarVenta(venta);

        out.print("{\"success\":" + resultado + "}");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        boolean resultado = ventaService.eliminarVenta(id);

        out.print("{\"success\":" + resultado + "}");
        out.flush();
    }
}