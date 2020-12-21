package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.CardServiceDAO;
import ua.lviv.iot.model.entity.CardService;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CardServiceDaoImpl implements CardServiceDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.card_service";
    private static final String FIND_BY_ID = "SELECT * FROM"
            + "mydb.card_service WHERE id=?";
    private static final String CREATE = "INSERT mydb.card_service "
            + "(service_id, card_type_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE mydb.card_service"
            + " SET service_id=?, card_type_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM mydb.card_service"
            + "WHERE id=?";

    @Override
    public List<CardService> findAll() throws SQLException {
        List<CardService> cardServices = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CardService cardService = new CardService(
                        resultSet.getInt("id"),
                        resultSet.getInt("service_id"),
                        resultSet.getInt("card_type_id")
                );
                cardServices.add(cardService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardServices;
    }

    @Override
    public CardService findById(final Integer id) throws SQLException {
        CardService cardService = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cardService = new CardService(
                        resultSet.getInt("id"),
                        resultSet.getInt("service_id"),
                        resultSet.getInt("card_type_id")

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardService;
    }

    @Override
    public void create(final CardService cardService) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, String.valueOf(cardService.getServiceId()));
            statement.setString(2, String.valueOf(cardService.getCardTypeId()));

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final CardService cardService) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {

            statement.setInt(1, cardService.getServiceId());
            statement.setInt(2, cardService.getCardTypeId());
            statement.setInt(3, cardService.getId());

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
