package ua.nure.perets.SummaryTask4.dao.impl;

import ua.nure.perets.SummaryTask4.bean.Question;
import ua.nure.perets.SummaryTask4.dao.QuestionDao;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.perets.SummaryTask4.db.DBManager.*;
import static ua.nure.perets.SummaryTask4.db.DBManager.closeConnection;

public class QuestionDaoImpl implements QuestionDao {

    private static final String ID = "question_id";
    private static final String NAME = "name";
    private static final String TEST_ID = "test_id";

    private static final String UPDATE_QUESTION_NAME = "UPDATE question SET name = ? WHERE question_id = ?";
    private static final String GET_ALL_QUESTIONS = "SELECT * FROM question";
    private static final String CREATE_QUESTION = "INSERT INTO question VALUE (DEFAULT, ?, ?)";
    private static final String DELETE_QUESTION = "DELETE FROM question WHERE question_id = ?";
    private static final String GET_QUESTIONS_BY_TEST = "SELECT * FROM question WHERE test_id = ?";

    @Override
    public void updateQuestionName(String name, String id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_QUESTION_NAME);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setString(i++, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update question name", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    @Override
    public List<Question> getQuestions() throws DBException {
        List<Question> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, GET_ALL_QUESTIONS);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getQuestion(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot get questions", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

        return list;
    }

    @Override
    public void createQuestion(String name, int testId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, CREATE_QUESTION);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setInt(i++, testId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot create question", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void deleteQuestion(String questionId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, DELETE_QUESTION);
            preparedStatement.setInt(1, Integer.parseInt(questionId));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot delete question", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Question> findQuestionsByTestId(int testId) throws DBException {
        List<Question> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, GET_QUESTIONS_BY_TEST);
            preparedStatement.setInt(1, testId);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getQuestion(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot find question by test id", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    private Question getQuestion(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getInt(ID));
        question.setName(resultSet.getString(NAME));
        question.setTestId(resultSet.getInt(TEST_ID));
        return question;
    }
}
