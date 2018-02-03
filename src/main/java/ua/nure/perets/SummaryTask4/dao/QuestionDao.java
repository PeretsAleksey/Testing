package ua.nure.perets.SummaryTask4.dao;

import ua.nure.perets.SummaryTask4.bean.Question;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.util.List;

public interface QuestionDao {

    void updateQuestionName(String name, String id) throws DBException;

    List<Question> getQuestions() throws DBException;

    void createQuestion(String name, int testId) throws DBException;

    void deleteQuestion(String questionId) throws DBException;

    List<Question> findQuestionsByTestId(int testId) throws DBException;
}