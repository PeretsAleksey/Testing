package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
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

    private static final Logger LOG = Logger.getLogger(AnswerStatusCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        AnswerDaoImpl answerDao = new AnswerDaoImpl();

        String id = req.getParameter("answerId");

        LOG.trace("Request parameter: id --> " + id);

        answerDao.updateAnswerStatusById(id);

        List<Answer> list = answerDao.findAnswersByQuestionId((Integer) session.getAttribute("questionId"));
        session.setAttribute("answersList", list);
        LOG.trace("Set the session attribute: answersList --> " + list);

        page = Path.EDIT_QUESTION_PAGE;

        LOG.debug("Command finished");

        return page;
    }
}