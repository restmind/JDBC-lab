package ua.lviv.iot.DAO.implementation;

import ua.lviv.iot.DAO.GymDAO;
import ua.lviv.iot.model.entity.Gym;
import ua.lviv.iot.persistant.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class GymDaoImpl implements GymDAO {
    private static final String FIND_ALL = "SELECT * FROM mydb.gym";
    private static final String FIND_BY_ID = "SELECT * FROM mydb.gym WHERE id=?";
    private static final String CREATE = "INSERT mydb.gym "
            + "(location) VALUES (?)";
    private static final String UPDATE = "UPDATE mydb.gym"
            + " SET location=? WHERE id=?";
    private static final String DELETE = "DELETE FROM mydb.gym WHERE id=?";

    @Override
    public List<Gym> findAll() throws SQLException {
        List<Gym> gyms = new ArrayList<>();
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Gym gym = new Gym(
                        resultSet.getInt("id"),
                        resultSet.getString("location")
                );
                gyms.add(gym);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gyms;
    }

    @Override
    public Gym findById(final Integer id) throws SQLException {
        Gym gym = null;
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                gym = new Gym(
                        resultSet.getInt("id"),
                        resultSet.getString("location")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gym;
    }

    @Override
    public void create(final Gym gym) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(CREATE)) {

            statement.setString(1, String.valueOf(gym.getLocation()));

            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(final Integer id, final Gym gym) throws SQLException {
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(UPDATE)) {

            statement.setString(1, gym.getLocation());
            statement.setInt(2, gym.getId());

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
