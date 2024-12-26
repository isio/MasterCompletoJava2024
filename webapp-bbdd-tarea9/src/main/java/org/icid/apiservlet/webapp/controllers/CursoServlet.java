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

@WebServlet({"/index.html", "/cursos"})
public class CursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) request.getAttribute("conn");
        CursoService cursoService = new CursoServiceImpl(conn);
        List<Curso> cursos = cursoService.listar();

        request.setAttribute("titulo", "Tarea 9 : Listado de Cursos");
        request.setAttribute("cursos", cursos);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(request, response);
    }
}
