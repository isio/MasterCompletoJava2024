package org.icid.apiservlet.webapp.repositories;

import org.icid.apiservlet.webapp.models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepositoryImpl implements Repository<Curso> {

    private Connection conn;

    public CursoRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Curso> listar() throws SQLException {
        List<Curso> cursos = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cursos")) {

            while (rs.next()) {
                Curso curso = getCurso(rs);
                cursos.add(curso);
            }
        }
        return cursos;
    }

    @Override
    public List<Curso> porNombre(String nombre) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cursos as c WHERE c.nombre like ?")) {
            stmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cursos.add(getCurso(rs));
                }
            }
        }
        return cursos;
    }

    private Curso getCurso(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setId(rs.getLong("id"));
        curso.setNombre(rs.getString("nombre"));
        curso.setDescripcion(rs.getString("descripcion"));
        curso.setInstructor(rs.getString("instructor"));
        curso.setDuracion(rs.getInt("duracion"));
        return curso;
    }
}
