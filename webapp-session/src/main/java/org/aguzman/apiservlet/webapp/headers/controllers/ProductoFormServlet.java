package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        req.setAttribute("categorias", service.listarCategoria());

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        Producto producto = new Producto();
        if ( id > 0 ) {
            Optional<Producto> optional = service.porId(id);
            if ( optional.isPresent() ) {
                producto = optional.get();
            }
        }

        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        String nombre = req.getParameter("nombre");

        Integer precio;
        try {
            precio = Integer.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");

        Long categoriaId;
        try {
            categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if(nombre == null || nombre.isEmpty()) {
            errores.put("nombre", "El nombre es requerido!");
        }

        if(sku == null || sku.isEmpty()) {
            errores.put("sku", "El Sku es requerido!");
        } else if(sku.length() > 10) {
            errores.put("sku", "El Sku debe tener como máximo 10 caracteres!");
        }

        if(fechaStr == null || fechaStr.isEmpty()) {
            errores.put("fecha_registro", "La fecha es requerida!");
        }

        if(precio.equals(0)) {
            errores.put("precio", "El precio es requerido!");
        }

        if(categoriaId.equals(0L)) {
            errores.put("categoria", "La categoría es requerida!");
        }

        if(errores.isEmpty()) {
            LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setSku(sku);
            producto.setPrecio(precio);
            producto.setFechaRegistro(fecha);

            Categoria categoria = new Categoria();
            categoria.setId(categoriaId);
            producto.setCategoria(categoria);

            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            this.doGet(req, resp);
        }
    }
}
