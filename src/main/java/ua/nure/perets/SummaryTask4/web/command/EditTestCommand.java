package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Question;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.QuestionDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditTestCommand extends Command {

    private static final String TEST_TIME = "testTime";
    private static final String TEST_DIFFICULTY = "testDifficulty";
    private static final String TEST_ID = "testId";
    private static final String TEST_NAME = "testName";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        HttpSession session = req.getSession(false);
        String page = Path.ERROR_PAGE;
        QuestionDaoImpl questionDao = new QuestionDaoImpl();
        User user = (User) session.getAttribute("user");

        if (user.getRoleId() == 1) {
            throw new AppException("access denied");
        }

        int testId;

        if (req.getParameter(TEST_ID) != null) {
            testId = Integer.valueOf(req.getParameter(TEST_ID));
        } else {
            testId = (int) session.getAttribute(TEST_ID);
        }

        String testName;
        String testDifficulty;
        String testTime;

        if (req.getParameter(TEST_NAME) != null) {
            testName = req.getParameter(TEST_NAME);
        } else {
            testName = (String) session.getAttribute(TEST_NAME);
        }

        if (req.getParameter(TEST_DIFFICULTY) != null) {
            testDifficulty = req.getParameter(TEST_DIFFICULTY);
        } else {
            testDifficulty = (String) session.getAttribute(TEST_DIFFICULTY);
        }

        if (req.getParameter(TEST_TIME) != null) {
            testTime = req.getParameter(TEST_TIME);
        } else {
            testTime = (String) session.getAttribute(TEST_TIME);
        }


        List<Question> list =  questionDao.findQuestionsByTestId(testId);

        session.setAttribute(TEST_NAME, testName);
        session.setAttribute(TEST_DIFFICULTY, testDifficulty);
        session.setAttribute(TEST_TIME, testTime);
        session.setAttribute("questionList", list);
        session.setAttribute(TEST_ID, testId);

        page = Path.EDIT_TEST_PAGE;

        return page;
    }
}
