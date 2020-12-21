package ua.lviv.iot.DAO.implementation;


import ua.lviv.iot.DAO.ClientCardDAO;
import ua.lviv.iot.model.entity.ClientCard;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ClientCardDaoImpl implements ClientCardDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.client_card";
    private static final String FIND_BY_ID = "SELECT * FROM mydb.client_card WHERE client_id=?";
    private static final String CREATE = "INSERT mydb.client_card "
            + "(client_id, card_type_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE mydb.client_card"
            + " SET card_type_id=?  WHERE client_id=?";
    private static final String DELETE = "DELETE FROM mydb.client_card WHERE client_id=?";

    @Override
    public List<ClientCard> findAll() throws SQLException {
        List<ClientCard> clientCards = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClientCard clientCard = new ClientCard(
                        resultSet.getInt("client_id"),
                        resultSet.getInt("card_type_id")

                );
                clientCards.add(clientCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientCards;
    }

    @Override
    public ClientCard findById(final Integer id) throws SQLException {
        ClientCard clientCard = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                clientCard = new ClientCard(
                        resultSet.getInt("client_id"),
                        resultSet.getInt("card_type_id")

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientCard;
    }

    @Override
    public void create(final ClientCard clientCard) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, String.valueOf(clientCard.getClientId()));
            statement.setString(2, String.valueOf(clientCard.getCardTypeId()));

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final ClientCard clientCard) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {

            statement.setInt(2, clientCard.getClientId());
            statement.setInt(1, clientCard.getCardTypeId());

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
