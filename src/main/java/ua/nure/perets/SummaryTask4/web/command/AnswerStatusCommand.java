package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Answer;
import ua.nure.perets.SummaryTask4.dao.impl.AnswerDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AnswerStatusCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        AnswerDaoImpl answerDao = new AnswerDaoImpl();

        String id = req.getParameter("answerId");

        answerDao.updateAnswerStatusById(id);

        List<Answer> list = answerDao.findAnswersByQuestionId((Integer) session.getAttribute("questionId"));
        session.setAttribute("answersList", list);
        page = Path.EDIT_QUESTION_PAGE;
        return page;
    }
}