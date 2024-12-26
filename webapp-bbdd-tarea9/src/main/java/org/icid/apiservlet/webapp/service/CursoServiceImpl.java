package org.icid.apiservlet.webapp.service;

import org.icid.apiservlet.webapp.exceptions.CursoServiceException;
import org.icid.apiservlet.webapp.models.Curso;
import org.icid.apiservlet.webapp.repositories.Repository;
import org.icid.apiservlet.webapp.repositories.CursoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CursoServiceImpl implements CursoService {

    private Repository<Curso> cursoRepository;

    public CursoServiceImpl(Connection connection) {
        this.cursoRepository = new CursoRepositoryImpl(connection);
    }

    @Override
    public List<Curso> listar() {
        try {
            return this.cursoRepository.listar();
        } catch (SQLException throwables) {
            throw new CursoServiceException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Curso> porNombre(String nombre) {
        try {
            return this.cursoRepository.porNombre(nombre);
        } catch (SQLException throwables) {
            throw new CursoServiceException(throwables.getMessage(), throwables.getCause());
        }
    }
}
