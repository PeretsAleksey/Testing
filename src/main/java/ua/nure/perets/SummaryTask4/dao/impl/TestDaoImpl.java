package ua.nure.perets.SummaryTask4.dao.impl;

import ua.nure.perets.SummaryTask4.bean.Test;
import ua.nure.perets.SummaryTask4.dao.TestDao;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.perets.SummaryTask4.db.DBManager.*;
import static ua.nure.perets.SummaryTask4.db.DBManager.closeConnection;
import static ua.nure.perets.SummaryTask4.db.DBManager.closePreparedStatement;

public class TestDaoImpl implements TestDao {

    private static final String ID = "test_id";
    private static final String NAME = "name";
    private static final String DIFFICULTY = "difficulty";
    private static final String TIME_FOR_TEST = "time_for_test";
    private static final String THEME_ID = "theme_id";

    private static final String UPDATE_TEST_NAME = "UPDATE test SET name = ? WHERE test_id = ?";
    private static final String UPDATE_TEST_DIFFICULTY = "UPDATE test SET difficulty = ? WHERE test_id = ?";
    private static final String UPDATE_TEST_TIME = "UPDATE test SET time_for_test = ? WHERE test_id = ?";
    private static final String CREATE_TEST = "INSERT INTO test VALUE (DEFAULT, ?, ?, DEFAULT, ?, ?)";
    private static final String GET_TESTS_BY_THEME = "SELECT * FROM test WHERE theme_id = ?";
    private static final String DELETE_TEST = "DELETE FROM test WHERE test_id =?";
    private static final String FIND_TESTS = "SELECT * FROM test";

    @Override
    public List<Test> getTests() throws DBException {
        List<Test> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, FIND_TESTS);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getTest(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot find all tests", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public void updateTestName(String name, String id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_TEST_NAME);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setString(i++, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update test name", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void updateTestDifficulty(String difficulty, String id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_TEST_DIFFICULTY);
            int i = 1;
            preparedStatement.setString(i++, difficulty);
            preparedStatement.setString(i++, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update test difficulty", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void updateTestTime(String time, String id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_TEST_TIME);
            int i = 1;
            preparedStatement.setString(i++, time);
            preparedStatement.setString(i++, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update test time", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void createTest(String name, String difficulty, String time, int themeId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, CREATE_TEST);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setString(i++, difficulty);
            preparedStatement.setInt(i++, Integer.parseInt(time));
            preparedStatement.setInt(i++, themeId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot create test", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Test> findTestsByTheme(int themeId) throws DBException {
        List<Test> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, GET_TESTS_BY_THEME);
            preparedStatement.setInt(1, themeId);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getTest(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot find test by theme id", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    @Override
    public void deleteTest(String testId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, DELETE_TEST);
            preparedStatement.setInt(1, Integer.parseInt(testId));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot delete test", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public int findTimeForTestByTestId(int testId) throws DBException {
        int time = 0;
        List<Test> tests = getTests();
        for (Test test : tests){
            if (test.getId() == testId){
                time = test.getTimeForTest();
            }
        }
        return time;
    }


    private Test getTest(ResultSet resultSet) throws SQLException, DBException {
        Test test = new Test();
        test.setId(resultSet.getInt(ID));
        test.setName(resultSet.getString(NAME));
        test.setDifficulty(resultSet.getString(DIFFICULTY));
        test.setQuestionsCount(new QuestionDaoImpl().findQuestionsByTestId(test.getId()).size());
        test.setTimeForTest(resultSet.getInt(TIME_FOR_TEST));
        test.setThemesId(resultSet.getInt(THEME_ID));
        return test;
    }
}
