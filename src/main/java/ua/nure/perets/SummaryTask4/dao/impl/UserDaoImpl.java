package ua.nure.perets.SummaryTask4.dao.impl;

import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.UserDao;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.perets.SummaryTask4.db.DBManager.*;

public class UserDaoImpl implements UserDao {

    private static final String ID = "user_id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String F_NAME = "first_name";
    private static final String L_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String BLOCKING = "blocking";
    private static final String ROLE_ID = "role_id";


    private static final String GET_ALL_USER = "SELECT * FROM user";
    private static final String ADD_USER = "INSERT INTO user VALUES(DEFAULT,?,?,?,?,?,DEFAULT,1)";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login = ?";
    private static final String UPDATE_USER_STATUS = "UPDATE user SET blocking = ? WHERE login = ?";


    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = getPreparedStatement(connection, GET_ALL_USER);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeResultSet(resultSet);
        closePreparedStatement(preparedStatement);
        closeConnection(connection);
        return users;
    }

    public void addUser(List<User> users) {

    }

    public void delUser(int id) {

    }

    public boolean updateUserStatusByLogin(String login) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Boolean status = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_USER_STATUS);
            User user = getUserByLogin(login);
            int k = 1;
            if (user.isBlocked()) {
                preparedStatement.setBoolean(k++, false);
                status = true;
            } else {
                preparedStatement.setBoolean(k++, true);
                status = false;
            }
            preparedStatement.setString(k++, user.getLogin());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot update user", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

        return status;
    }

    public void createUser(String login, String password, String fName, String lName, String eMail) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ADD_USER);
            int i = 1;
            preparedStatement.setString(i++, login);
            preparedStatement.setString(i++, password);
            preparedStatement.setString(i++, fName);
            preparedStatement.setString(i++, lName);
            preparedStatement.setString(i++, eMail);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot create user", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    public User getUserByLogin(String login) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = getPreparedStatement(connection, GET_USER_BY_LOGIN);
        try {
            preparedStatement.setString(1, login);
            ResultSet resultSet = getResultSet(preparedStatement);
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
            closeResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setfName(resultSet.getString(F_NAME));
        user.setlName(resultSet.getString(L_NAME));
        user.seteMail(resultSet.getString(EMAIL));
        user.setRoleId(resultSet.getInt(ROLE_ID));
        user.setBlocked(resultSet.getBoolean(BLOCKING));

        return user;
    }

    private void setUserFromResultSetAndExecute(PreparedStatement preparedStatement, User user) {
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
