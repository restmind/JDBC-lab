package ua.lviv.iot.service;

import ua.lviv.iot.DAO.GeneralDAO;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractService<T> implements Service<T> {
    public abstract GeneralDAO<T> getGeneralDAO();
    @Override
    public List<T> findAll() throws SQLException {
        return getGeneralDAO().findAll();
    }

    @Override
    public T findById(final Integer id) throws SQLException {
        return getGeneralDAO().findById(id);
    }

    @Override
    public void create(final T object) throws SQLException {
        getGeneralDAO().create(object);
    }

    @Override
    public void update(final Integer id, final T object) throws SQLException {
        getGeneralDAO().update(id, object);
    }

    @Override
    public void delete(final Integer id) throws SQLException {
        getGeneralDAO().delete(id);
    }
}
