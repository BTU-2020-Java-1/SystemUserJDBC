package ge.edu.btu.user.dao;

import ge.edu.btu.user.model.SystemUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUserDAOImpl implements SystemUserDAO {

    private Connection connection;

    public SystemUserDAOImpl() throws Exception {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BTUUser", "postgres", "1");
    }

    @Override
    public void addUser(SystemUser user) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO system_user (username, password, active) VALUES (?, ?, ?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setBoolean(3, user.isActive());

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void addUsers(List<SystemUser> users) {

    }

    @Override
    public void deleteById(long id) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM system_user WHERE id = " + id);
        statement.close();
    }

    @Override
    public void setActiveForUser(long id, boolean active) {

    }

    @Override
    public List<SystemUser> getAll() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet =  statement.executeQuery("SELECT * FROM system_user");

        List<SystemUser> list = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean active = resultSet.getBoolean("active");
            SystemUser systemUser = new SystemUser(id, username, password, active);
            list.add(systemUser);
        }

        statement.close();

        return list;
    }

    public void closeConnection() throws Exception {
        connection.close();
    }
}
