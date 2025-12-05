package com.olvealmacen.tienda.controlador;

import com.olvealmacen.tienda.dao.CategoriaDAO;
import com.olvealmacen.tienda.modelo.Categoria;
import com.google.gson.Gson;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriaControlador", value = "/CategoriaControlador")
public class CategoriaControlador extends HttpServlet {

    CategoriaDAO dao = new CategoriaDAO();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                List<Categoria> lista = dao.listar();
                String json = gson.toJson(lista);

                response.setContentType("application/json");
                response.getWriter().write(json);
                break;

            default:
                response.getWriter().write("{\"error\":\"Acción no válida\"}");
                break;
        }
    }
}
