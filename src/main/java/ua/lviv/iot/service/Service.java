package ua.lviv.iot.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<E> {
    List<E> findAll() throws SQLException;

    E findById(Integer id) throws SQLException;

    void create(E object) throws SQLException;

    void update(Integer id, E object) throws SQLException;

    void delete(Integer id) throws SQLException;

}
