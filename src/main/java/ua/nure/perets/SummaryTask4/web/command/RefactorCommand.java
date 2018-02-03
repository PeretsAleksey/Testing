package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Answer;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.AnswerDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.QuestionDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.TestDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.ThemeDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.Validators;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RefactorCommand extends Command {

    private static final String NAME = "name";
    private static final String THEME = "Theme";
    private static final String TYPE = "type";
    private static final String TEST = "Test";
    private static final String TEST_ID = "testId";
    private static final String TIME = "Time";
    private static final String ANSWER = "Answer";
    private static final String QUESTION = "Question";
    private static final String QUESTION_ID = "questionId";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;

        ThemeDaoImpl themeDao = new ThemeDaoImpl();
        TestDaoImpl testDao = new TestDaoImpl();
        AnswerDaoImpl answerDao = new AnswerDaoImpl();
        QuestionDaoImpl questionDao = new QuestionDaoImpl();

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        if (user.getRoleId() == 1) {
            throw new AppException("access denied");
        }

        String name = req.getParameter(NAME);

        if ("theme".equals(session.getAttribute(NAME))) {

            if (Validators.validateSentences(name, THEME, 100) != null) {
                throw new AppException(Validators.validateSentences(name, THEME, 100));
            } else if (name.length() == 0) {
                throw new AppException("theme name is empty");
            }

            String themeId = String.valueOf(session.getAttribute("theme"));
            themeDao.updateThemeName(name, themeId);
            session.setAttribute("themesList", themeDao.getThemes());
            req.setAttribute(TYPE, session.getAttribute(NAME));
        } else if ("test".equals(session.getAttribute(NAME))) {
            if (Validators.validateSentences(name, TEST, 100) != null) {
                throw new AppException(Validators.validateSentences(name, TEST, 100));
            } else if (name.length() == 0) {
                throw new AppException("test name is empty");
            }

            String testId = String.valueOf(session.getAttribute(TEST_ID));
            testDao.updateTestName(name, testId);
            req.setAttribute(TYPE, session.getAttribute(NAME));
            session.setAttribute("testName", name);
        } else if ("testDifficulty".equals(session.getAttribute(NAME))) {
            if (Validators.validateDifficulty(name) != null) {
                throw new AppException(Validators.validateDifficulty(name));
            }

            String testId = String.valueOf(session.getAttribute(TEST_ID));
            testDao.updateTestDifficulty(name, testId);
            req.setAttribute(TYPE, session.getAttribute(NAME));
            session.setAttribute("testDifficulty", name);
        } else if ("testTime".equals(session.getAttribute(NAME))) {
            if (Validators.validateNumbers(name, TIME, 10) != null) {
                throw new AppException(Validators.validateNumbers(name, TIME, 10));
            }
            if (Integer.valueOf(name) == 0) {
                throw new AppException("time cannot be 0");
            }
            String testId = String.valueOf(session.getAttribute(TEST_ID));
            testDao.updateTestTime(name, testId);
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
            session.setAttribute("testTime", name);
        } else if ("answer".equals(session.getAttribute(NAME))) {
            if (Validators.validateSentences(name, ANSWER, 100) != null) {
                throw new AppException(Validators.validateSentences(name, ANSWER, 100));
            } else if (name.length() == 0) {
                throw new AppException("answer name is empty");
            }
            String answerId = String.valueOf(session.getAttribute("answerId"));
            answerDao.updateAnswerName(name, answerId);
            req.setAttribute(TYPE, session.getAttribute(NAME));
        } else if ("question".equals(session.getAttribute(NAME))) {
            if (Validators.validateSentences(name, QUESTION, 100) != null) {
                throw new AppException(Validators.validateSentences(name, QUESTION, 100));
            } else if (name.length() == 0) {
                throw new AppException("question name is empty");
            }

            String questionId = String.valueOf(session.getAttribute(QUESTION_ID));
            questionDao.updateQuestionName(name, questionId);
            req.setAttribute("type", (String) session.getAttribute(NAME));
            session.setAttribute("questionName", name);
            session.setAttribute("questionList", questionDao.getQuestions());
        } else if ("addTheme".equals(session.getAttribute(NAME))) {
            if (Validators.validateSentences(name, THEME, 100) != null) {
                throw new AppException(Validators.validateSentences(name, THEME, 100));
            } else if (name.length() == 0) {
                throw new AppException("theme name is empty");
            }

            themeDao.createTheme(name);
            System.out.println((String) session.getAttribute(NAME));
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
            session.setAttribute("themesList", themeDao.getThemes());
        } else if ("addTest".equals(session.getAttribute(NAME))) {

            if (Validators.validateSentences(name, TEST, 100) != null) {
                throw new AppException(Validators.validateSentences(name, TEST, 100));
            } else if (name.length() == 0) {
                throw new AppException("test name is empty");
            }

            String difficulty = req.getParameter("difficulty");
            String time = req.getParameter("time");
            int themeId = (Integer) session.getAttribute("theme");

            if (Validators.validateDifficulty(difficulty) != null) {
                throw new AppException(
                        Validators.validateDifficulty(difficulty));
            }
            if (Validators.validateNumbers(time, TIME, 10) != null) {
                throw new AppException(Validators.validateNumbers(time, TIME,
                        10));
            }
            if (Integer.valueOf(time) == 0) {
                throw new AppException("time cannot be 0");
            }

            testDao.createTest(name, difficulty, time, themeId);
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
            session.setAttribute("testName", name);
            session.removeAttribute("difficulty");
            session.removeAttribute("time");
        } else if ("addQuestion".equals(session.getAttribute(NAME))) {

            if (Validators.validateSentences(name, QUESTION, 100) != null) {
                throw new AppException(Validators.validateSentences(name, QUESTION, 100));
            } else if (name.length() == 0) {
                throw new AppException("question name is empty");
            }

            questionDao.createQuestion(name, (Integer) session.getAttribute("testId"));
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
            session.setAttribute("questionList", questionDao.getQuestions());
        } else if ("addAnswer".equals(session.getAttribute(NAME))) {

            if (Validators.validateSentences(name, ANSWER, 100) != null) {
                throw new AppException(Validators.validateSentences(name, ANSWER, 100));
            } else if (name.length() == 0) {
                throw new AppException("answer name is empty");
            }

            int questionId = (Integer) session.getAttribute("questionId");
            List<Answer> answers = answerDao.findAnswersByQuestionId((Integer) session.getAttribute(QUESTION_ID));
            for (Answer answer : answers) {
                if (answer.getName().contains(name)) {
                    throw new AppException("Answer: " + name + " is already exists in this question");
                }
            }

            answerDao.createAnswer(name, questionId);
            req.setAttribute(TYPE, session.getAttribute(NAME));
        } else if ("deleteTheme".equals(session.getAttribute(NAME))) {

            String themeId = String.valueOf(session.getAttribute("themeId"));
            themeDao.deleteTheme(themeId);
            session.setAttribute("themesList", themeDao.getThemes());
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
        } else if ("deleteTest".equals((String) session.getAttribute(NAME))) {

            String testId = String.valueOf(session.getAttribute("testId"));
            testDao.deleteTest(testId);
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
            session.setAttribute("testName", name);
        } else if ("deleteQuestion".equals(session.getAttribute(NAME))) {

            String questionId = String.valueOf(session.getAttribute(QUESTION_ID));
            questionDao.deleteQuestion(questionId);
            session.setAttribute("questionList", questionDao.getQuestions());
            req.setAttribute(TYPE, (String) session.getAttribute(NAME));
        } else if ("deleteAnswer".equals(session.getAttribute(NAME))) {

            String answerId = String.valueOf(session.getAttribute("answerId"));
            answerDao.deleteAnswer(answerId);
            req.setAttribute("type", (String) session.getAttribute(NAME));
        }


        session.removeAttribute(NAME);

        page = Path.ACTION_PAGE;

        return page;
    }
}
