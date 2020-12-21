package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.ClientDAO;
import ua.lviv.iot.model.entity.Client;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class ClientDaoImpl implements ClientDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.client";
    private static final String FIND_BY_ID = "SELECT * FROM mydb.client WHERE id=?";
    private static final String CREATE = "INSERT mydb.client "
            + "(name, surname, email) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE mydb.client"
            + " SET name=?, surname=?, email=? WHERE id=?";
    private static final String DELETE = "DELETE FROM mydb.client WHERE id=?";


    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email")
                );
                clients.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findById(final Integer id) {
        Client client = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void create(final Client client) {

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, String.valueOf(client.getName()));
            statement.setString(2, String.valueOf(client.getSurname()));
            statement.setString(3, String.valueOf(client.getEmail()));

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final Client client) {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {

            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setString(3, client.getEmail());
            statement.setInt(4, client.getId());

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(final Integer id) {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(DELETE)) {
            statement.setInt(1, id);
            System.out.println(statement);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

