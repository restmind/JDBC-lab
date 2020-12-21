package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.CardTypeDAO;
import ua.lviv.iot.model.entity.CardType;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public final class CardTypeDaoImpl implements CardTypeDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.card_type";
    private static final String FIND_BY_ID = "SELECT * FROM mydb.card_type WHERE id=?";
    private static final String CREATE = "INSERT mydb.card_type "
            + "(name) VALUES (?)";
    private static final String UPDATE = "UPDATE mydb.card_type"
            + " SET name=?  WHERE id=?";
    private static final String DELETE = "DELETE FROM mydb.card_type WHERE id=?";

    @Override
    public List<CardType> findAll() {
        List<CardType> cardTypes = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CardType cardType = new CardType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")

                );
                cardTypes.add(cardType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardTypes;
    }

    @Override
    public CardType findById(final Integer id) {
        CardType cardType = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cardType = new CardType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")

                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardType;
    }

    @Override
    public void create(final CardType cardType) {

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, cardType.getName());


            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final CardType cardType) {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {
            statement.setString(1, cardType.getName());
            statement.setInt(2, cardType.getId());

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
