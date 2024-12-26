package org.icid.apiservlet.webapp.service;

import org.icid.apiservlet.webapp.models.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();
    List<Curso> porNombre(String nombre);


}
