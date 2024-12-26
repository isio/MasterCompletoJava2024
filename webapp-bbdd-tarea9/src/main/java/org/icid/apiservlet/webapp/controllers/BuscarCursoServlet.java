package org.icid.apiservlet.webapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.icid.apiservlet.webapp.models.Curso;
import org.icid.apiservlet.webapp.service.CursoService;
import org.icid.apiservlet.webapp.service.CursoServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/cursos/buscar")
public class BuscarCursoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService cursoService = new CursoServiceImpl(conn);
        String nombre = req.getParameter("nombre");

        List<Curso> cursos = cursoService.porNombre(nombre);
        req.setAttribute("titulo", "Tarea 9 : Filtrando Cursos");
        req.setAttribute("cursos", cursos);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
