package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Test;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.TestDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.Comparators;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GetTestsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetTestsCommand.class);

    private static final String THEME = "theme";
    private static final String THEME_NAME = "themeName";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        String page = Path.ERROR_PAGE;
        TestDaoImpl testDao = new TestDaoImpl();

        HttpSession session = req.getSession(false);

        int theme = -1;

        if (session.getAttribute(THEME) != null) {
            theme = (Integer) session.getAttribute(THEME);
        }

        if (req.getParameter(THEME) != null) {
            theme = Integer.valueOf(req.getParameter(THEME));
        }

        String sort = req.getParameter("sort");
        LOG.trace("Request parameter: sort --> " + sort);

        List<Test> tests = testDao.findTestsByTheme(theme);

        if ("name".equals(sort)) {
            Collections.sort(tests, new Comparators.CompareByName());
        } else if ("diff".equals(sort)) {
            Collections.sort(tests, new Comparators.CompareByDifficulty());
        } else if ("qcount".equals(sort)) {
            Collections.sort(tests, new Comparators.CompareByQuestionsCount());
        }

        String themeName;

        if (req.getParameter(THEME_NAME) != null) {
            themeName = req.getParameter(THEME_NAME);
        } else {
            themeName = String.valueOf(session.getAttribute(THEME_NAME));
        }

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        if (user.getRoleId() == 1 && tests.size() == 0) {
            throw new AppException("No tests in this theme");
        }

        session.setAttribute(THEME_NAME, themeName);
        LOG.trace("Set the session attribute: themeName --> " + themeName);
        session.setAttribute("tests", tests);
        LOG.trace("Set the session attribute: tests --> " + tests);
        session.setAttribute(THEME, theme);
        LOG.trace("Set the session attribute: theme --> " + theme);

        page = Path.TEST_PAGE;

        LOG.debug("Command finished");

        return page;

    }
}
