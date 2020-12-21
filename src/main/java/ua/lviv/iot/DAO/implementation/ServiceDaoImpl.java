package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.ServiceDAO;
import ua.lviv.iot.model.entity.Service;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ServiceDaoImpl implements ServiceDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.service";
    private static final String FIND_BY_ID = "SELECT * FROM mydb.service WHERE id=?";
    private static final String CREATE = "INSERT mydb.service "
            + "(name, price) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE mydb.service"
            + " SET name=?, price=? WHERE id=?";
    private static final String DELETE = "DELETE FROM mydb.service WHERE id=?";

    @Override
    public List<Service> findAll() throws SQLException {
        List<Service> services = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")
                );
                services.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public Service findById(final Integer id) throws SQLException {
        Service service = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                service = new Service(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public void create(final Service service) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, String.valueOf(service.getName()));
            statement.setString(2, String.valueOf(service.getPrice()));

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final Service service) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {

            statement.setString(1, service.getName());
            statement.setBigDecimal(2, service.getPrice());
            statement.setInt(3, service.getId());

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(final Integer id) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(DELETE)) {
            statement.setInt(1, id);
            System.out.println(statement);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
