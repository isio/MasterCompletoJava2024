package org.icid.apiservlet.webapp.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    List<T> listar() throws SQLException;
    List<T> porNombre(String nombre) throws SQLException;
}
