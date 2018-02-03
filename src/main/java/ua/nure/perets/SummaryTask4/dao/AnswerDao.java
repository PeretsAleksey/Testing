package ua.nure.perets.SummaryTask4.dao;

import ua.nure.perets.SummaryTask4.bean.Answer;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.util.List;

public interface AnswerDao {

    void updateAnswerName(String name, String id) throws DBException;

    List<Answer> findAnswersByQuestionId(int id) throws DBException;

    void createAnswer(String name, int questionId) throws DBException;

    void deleteAnswer(String answerId) throws DBException;

    void updateAnswerStatusById(String id) throws DBException;

    List<Answer> getAnswers() throws DBException;

    Answer findAnswerById(String id) throws DBException;

    List<Answer> getCorrectAnswers(int id) throws DBException;
}
