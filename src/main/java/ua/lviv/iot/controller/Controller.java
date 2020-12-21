package ua.lviv.iot.controller;

import ua.lviv.iot.service.Service;

import java.sql.SQLException;

public interface Controller<T> {
    void findAll() throws SQLException;

    void findById() throws SQLException;

    void create(T object) throws SQLException;

    void update(Integer id, T object) throws SQLException;

    void delete() throws SQLException;

    Service<T> getService();

}
