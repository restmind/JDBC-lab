package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.OrderDAO;
import ua.lviv.iot.model.entity.Order;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class OrderDaoImpl implements OrderDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.order";
    private static final String FIND_BY_ID = "SELECT * FROM mydb.order WHERE id=?";
    private static final String CREATE = "INSERT mydb.order "
            + "(client_id, service_id, gym_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE mydb.order"
            + " SET client_id=?, service_id=?, gym_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM mydb.order WHERE id=?";

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getInt("service_id"),
                        resultSet.getInt("gym_id")

                );
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findById(final Integer id) throws SQLException {
        Order order = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("client_id"),
                        resultSet.getInt("service_id"),
                        resultSet.getInt("gym_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void create(final Order order) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, String.valueOf(order.getClientId()));
            statement.setString(2, String.valueOf(order.getServiceId()));
            statement.setString(3, String.valueOf(order.getGymId()));

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final Order order) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {


            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getServiceId());
            statement.setInt(3, order.getGymId());
            statement.setInt(4, order.getId());

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
