package ua.nure.perets.SummaryTask4.dao.impl;

import ua.nure.perets.SummaryTask4.bean.UserTest;
import ua.nure.perets.SummaryTask4.dao.UserTestDao;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ua.nure.perets.SummaryTask4.db.DBManager.*;

public class UserTestDaoImpl implements UserTestDao {

    private static final String ID = "user_test_id";
    private static final String RESULT = "result";
    private static final String DIFFICULTY = "difficulty";
    private static final String DATE = "date";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    private static final String GET_ALL_USER_TEST = "SELECT user_test.user_test_id, user.first_name, user.last_name," +
            "theme.name, test.name, test.difficulty, user_test.date, user_test.result " +
            "FROM user, test, theme, user_test " +
            "WHERE user_test.user_id = user.user_id AND test.test_id = user_test.test_id " +
            "AND test.theme_id = theme.theme_id";

    private static final String GET_USER_TEST = "SELECT user_test.user_test_id, user.first_name, user.last_name, " +
            "theme.name, test.name, test.difficulty, user_test.date, user_test.result " +
            "FROM user, test, theme, user_test " +
            "WHERE user_test.user_id = ? AND user_test.user_id = user.user_id AND test.test_id = user_test.test_id " +
            "AND test.theme_id = theme.theme_id";

    private static final String INSERT_USERS_TESTS = "INSERT INTO user_test VALUES (default, ?, ?, ?, ?)";


    @Override
    public List<UserTest> getUsersTests() throws DBException {

        List<UserTest> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, GET_ALL_USER_TEST);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getUserTest(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot find all users_tests", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public List<UserTest> findUsersTestsByUserId(int userId) throws DBException {

        List<UserTest> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, GET_USER_TEST);
            preparedStatement.setInt(1, userId);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getUserTest(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot find users_tests by user id", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public void createUsersTests(int testId, int userId, double result) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, INSERT_USERS_TESTS);
            int i = 1;
            preparedStatement.setInt(i++, userId);
            preparedStatement.setInt(i++, testId);
            preparedStatement.setString(i++, new Date().toString());
            preparedStatement.setInt(i++, (int) result);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot create users_tests", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    private UserTest getUserTest(ResultSet resultSet) throws SQLException {
        UserTest userTest = new UserTest();
        userTest.setId(resultSet.getInt(ID));
        userTest.setfName(resultSet.getString(FIRST_NAME));
        userTest.setlName(resultSet.getString(LAST_NAME));
        userTest.setTestName(resultSet.getString(5));
        userTest.setDate(resultSet.getString(DATE));
        userTest.setResult(resultSet.getInt(RESULT));
        userTest.setThemeName(resultSet.getString(4));
        userTest.setTestDifficulty(resultSet.getString(DIFFICULTY));
        return userTest;
    }
}
