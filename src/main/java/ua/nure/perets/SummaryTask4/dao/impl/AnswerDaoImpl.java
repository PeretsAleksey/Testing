package ua.nure.perets.SummaryTask4.dao.impl;

import ua.nure.perets.SummaryTask4.bean.Answer;
import ua.nure.perets.SummaryTask4.dao.AnswerDao;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.perets.SummaryTask4.db.DBManager.*;

public class AnswerDaoImpl implements AnswerDao {

    private static final String ID = "answer_id";
    private static final String NAME = "name";
    private static final String CORRECT = "correct";
    private static final String QUESTION_ID = "question_id";

    private static final String UPDATE_ANSWER_NAME = "UPDATE answer SET name = ? WHERE answer_id = ?";
    private static final String GET_ANSWERS_BY_QUESTION = "SELECT * FROM answer WHERE question_id = ?";
    private static final String CREATE_ANSWER = "INSERT INTO answer VALUE (DEFAULT, ?, false, ?)";
    private static final String DELETE_ANSWER = "DELETE FROM answer WHERE answer_id = ?";
    private static final String UPDATE_ANSWER_STATUS = "UPDATE answer SET correct = ? WHERE answer_id = ?";
    private static final String FIND_ALL_ANSWERS = "SELECT * FROM answer";

    @Override
    public void updateAnswerName(String name, String id) throws DBException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_ANSWER_NAME);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setString(i++, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update answer name", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Answer> findAnswersByQuestionId(int id) throws DBException {
        List<Answer> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, GET_ANSWERS_BY_QUESTION);
            preparedStatement.setInt(1, id);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getAnswer(resultSet));
            }
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot find answer by question id", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

        return list;
    }

    @Override
    public void createAnswer(String name, int questionId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, CREATE_ANSWER);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setInt(i++, questionId);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot create answer", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }

    }

    @Override
    public void deleteAnswer(String answerId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, DELETE_ANSWER);
            preparedStatement.setInt(1, Integer.parseInt(answerId));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot delete answer", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void updateAnswerStatusById(String id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Boolean status = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_ANSWER_STATUS);
            Answer answer = findAnswerById(id);
            int i = 1;
            if (answer.isCorrect()) {
                preparedStatement.setBoolean(i++, false);
                status = true;
            } else {
                preparedStatement.setBoolean(i++, true);
                status = false;
            }
            preparedStatement.setInt(i++, answer.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update answer", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public Answer findAnswerById(String id) throws DBException {
        Answer answer = null;
        List<Answer> list = getAnswers();
        for (Answer an : list) {
            if (Integer.valueOf(id) == an.getId()) {
                answer = an;
                break;
            }
        }
        return answer;
    }

    @Override
    public List<Answer> getCorrectAnswers(int id) throws DBException {
        List<Answer> answers = findAnswersByQuestionId(id);
        List<Answer> correct = new ArrayList<>();
        for (Answer answer : answers){
            if (answer.isCorrect()){
                correct.add(answer);
            }
        }
        return correct;
    }

    public List<Answer> getAnswers() throws DBException {
        List<Answer> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, FIND_ALL_ANSWERS);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getAnswer(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot get answers", e);
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return list;
    }

    private Answer getAnswer(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setId(resultSet.getInt(ID));
        answer.setName(resultSet.getString(NAME));
        answer.setCorrect(resultSet.getBoolean(CORRECT));
        answer.setQuestionId(resultSet.getInt(QUESTION_ID));
        return answer;

    }
}
