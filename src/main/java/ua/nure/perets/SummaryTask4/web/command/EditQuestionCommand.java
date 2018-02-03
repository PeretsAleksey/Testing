package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Answer;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.AnswerDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditQuestionCommand extends Command {

    private static final String QUESTION_NAME = "questionName";
    private static final String QUESTION_ID = "questionId";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        AnswerDaoImpl answerDao = new AnswerDaoImpl();

        User user = (User) session.getAttribute("user");

        if (user.getRoleId() == 1) {
            throw new AppException("access denied");
        }

        String questionName;
        if (req.getParameter(QUESTION_NAME) != null) {
            questionName = req.getParameter(QUESTION_NAME);
        } else {
            questionName = (String) session.getAttribute(QUESTION_NAME);
        }

        int questionId;

        if (req.getParameter(QUESTION_ID) != null) {
            questionId = Integer.parseInt(req.getParameter(QUESTION_ID));
        } else {
            questionId = (int) session.getAttribute(QUESTION_ID);
        }
        List<Answer> answers = answerDao.findAnswersByQuestionId(questionId);

        session.setAttribute("questionId", questionId);
        session.setAttribute("answersList", answers);
        session.setAttribute("questionName", questionName);

        page = Path.EDIT_QUESTION_PAGE;

        return page;
    }
}
