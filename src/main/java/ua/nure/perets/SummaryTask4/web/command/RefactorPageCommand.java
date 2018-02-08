package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RefactorPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RefactorPageCommand.class);

    private static final String ANSWER_ID = "answerId";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        if (user.getRoleId() == 1) {
            throw new AppException("access denied");
        }

        String name = req.getParameter("name");
        LOG.trace("Request parameter: name --> " + name);

        if ("answer".equals(name)) {
            String answerId = req.getParameter(ANSWER_ID);
            session.setAttribute(ANSWER_ID, answerId);
            LOG.trace("Set the session attribute: answerId --> " + answerId);
        } else if ("deleteTheme".equals(name)) {
            String themeId = req.getParameter("theme");
            session.setAttribute("themeId", themeId);
            LOG.trace("Set the session attribute: themeId --> " + themeId);
        } else if ("deleteTest".equals(name)) {
            String testId = req.getParameter("testId");
            session.setAttribute("testId", testId);
            LOG.trace("Set the session attribute: testId --> " + testId);
        } else if ("deleteQuestion".equals(name)) {
            String questionId = req.getParameter("questionId");
            session.setAttribute("questionId", questionId);
            LOG.trace("Set the session attribute: questionId --> " + questionId);
        } else if ("deleteAnswer".equals(name)) {
            String answerId = req.getParameter(ANSWER_ID);
            LOG.trace("Request parameter: answerId --> " + answerId);
            session.setAttribute(ANSWER_ID, answerId);
            LOG.trace("Set the session attribute: answerId --> " + answerId);
        }

        session.setAttribute("name", name);
        LOG.trace("Set the session attribute: name --> " + name);

        page = Path.CHANGE_NAME_PAGE;

        LOG.debug("Command finished");

        return page;

    }
}
