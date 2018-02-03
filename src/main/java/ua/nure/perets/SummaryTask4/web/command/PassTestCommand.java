package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Answer;
import ua.nure.perets.SummaryTask4.bean.Question;
import ua.nure.perets.SummaryTask4.dao.impl.AnswerDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.QuestionDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.TestDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.UserTestDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class PassTestCommand extends Command {

    private static final String ANSWERED_QUESTIONS = "answeredQuestions";
    private static final String ANSWER_LIST = "answerList";
    private static final String TEST_ID = "testId";
    private static final String QUESTION_ID = "questionId";
    private static final String START_TIME = "startTime";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;
        TestDaoImpl testDao = new TestDaoImpl();
        QuestionDaoImpl questionDao = new QuestionDaoImpl();
        AnswerDaoImpl answerDao = new AnswerDaoImpl();
        UserTestDaoImpl userTestDao = new UserTestDaoImpl();

        HttpSession session = req.getSession(false);

        long startTime = 0;

        if (session.getAttribute(START_TIME) != null) {
            startTime = (Long) session.getAttribute(START_TIME);
        } else {
            startTime = System.currentTimeMillis();
        }

        int testId;
        int questionId;

        Map<Integer, String[]> answers;
        List<Integer> answeredQuestions;

        if (session.getAttribute(ANSWER_LIST) != null) {
            answers = (Map<Integer, String[]>) session.getAttribute(ANSWER_LIST);
        } else {
            answers = new TreeMap<>();
        }

        if (session.getAttribute(ANSWERED_QUESTIONS) != null) {
            answeredQuestions = (List<Integer>) session.getAttribute(ANSWERED_QUESTIONS);
        } else {
            answeredQuestions = new ArrayList<>();
        }

        String[] answerNames = null;

        if (req.getParameter(QUESTION_ID) != null) {
            questionId = Integer.valueOf(req.getParameter(QUESTION_ID));

        } else {
            questionId = (Integer) session.getAttribute(QUESTION_ID);
        }

        if (req.getParameter(TEST_ID) != null) {
            testId = Integer.valueOf(req.getParameter(TEST_ID));

        } else {
            if (req.getParameterValues("answer") != null) {
                answerNames = req.getParameterValues("answer");
            }
            testId = (Integer) session.getAttribute(TEST_ID);
        }

        long time = testDao.findTimeForTestByTestId(testId)*1000;

        List<Question> questionList = questionDao.findQuestionsByTestId(testId);

        if (questionList.size() == 0) {
            throw new AppException("Test contains no questions");
        }

        for (Question question : questionList) {
            if (answerDao.getCorrectAnswers(question.getId()).size() == 0) {
                throw new AppException("Question: " + question.getName()
                        + " contains no correct answers");
            }
        }

        if (req.getParameter("next") != null) {
            if (System.currentTimeMillis() - startTime > time) {
                req.setAttribute("timeout", true);
            }


            questionId += 1;

            if (questionId == questionList.size()) {
                questionId = 0;
            } else if (questionId < 0) {
                questionId = questionList.size() - 1;
            }

            while (answeredQuestions.contains(questionId)) {
                questionId += 1;
            }

            session.setAttribute("startTime", startTime);
            session.setAttribute(ANSWER_LIST, answers);
            session.setAttribute(ANSWERED_QUESTIONS, answeredQuestions);

        } else if (req.getParameter("previous") != null) {
            if (System.currentTimeMillis() - startTime > time) {
                req.setAttribute("timeout", true);
            }

            questionId -= 1;

            if (questionId == questionList.size()) {
                questionId = 0;
            } else if (questionId < 0) {
                questionId = questionList.size() - 1;
            }
            while (answeredQuestions.contains(questionId)) {
                questionId -= 1;
            }

            session.setAttribute(START_TIME, startTime);
            session.setAttribute(ANSWERED_QUESTIONS, answeredQuestions);
            req.setAttribute(START_TIME, startTime);

        } else if (req.getParameter("commit") != null) {

            if (System.currentTimeMillis() - startTime > time) {
                req.setAttribute("timeout", true);
            }
            answeredQuestions.add(questionId);

            answers.put(questionList.get(questionId).getId(), answerNames);

            if (questionList.size() != answeredQuestions.size()) {
                questionId += 1;

                while (answeredQuestions.contains(questionId)) {
                    questionId += 1;
                }
            }

            session.setAttribute(ANSWER_LIST, answers);
            session.setAttribute(ANSWERED_QUESTIONS, answeredQuestions);
            session.setAttribute(START_TIME, startTime);

        } else if (req.getParameter("end") != null) {

            double result;

            Set<Integer> resultList = new TreeSet<>();

            for (Map.Entry<Integer, String[]> entry : answers.entrySet()) {
                List<Answer> correct = answerDao.getCorrectAnswers(entry.getKey());
                List<String> names = new ArrayList<>();

                for (Answer answer : correct) {
                    names.add(answer.getName());
                }

                if (entry.getValue() == null) {
                    continue;
                }

                if ((Arrays.asList(entry.getValue())).containsAll(names)) {
                    resultList.add(entry.getKey());
                }
            }

            result = (double) resultList.size() / questionList.size() * 100;

            userTestDao.createUsersTests(testId, (Integer) session.getAttribute("userId"), result);

            session.setAttribute(ANSWERED_QUESTIONS, null);
            session.setAttribute("singleQuestion", null);
            session.setAttribute("questionCount", null);
            session.setAttribute(TEST_ID, null);
            session.setAttribute(QUESTION_ID, null);
            req.setAttribute("question", null);
            session.setAttribute("answers", null);
            session.setAttribute("questions", null);
            session.setAttribute(ANSWER_LIST, null);
            session.removeAttribute(START_TIME);

            page = Path.TEST_PAGE;
            return page;

        }

        if (questionId == questionList.size()) {
            questionId = 0;
            while (answeredQuestions.contains(questionId)) {
                questionId += 1;
            }
        } else if (questionId < 0) {
            questionId = questionList.size() - 1;
            while (answeredQuestions.contains(questionId)) {
                questionId -= 1;
            }
        }

        List<Answer> answerList = answerDao.findAnswersByQuestionId(questionList.get(questionId).getId());

        session.setAttribute("answeredQuestions", answeredQuestions);
        session.setAttribute("singleQuestion", questionList.size() - answeredQuestions.size());
        session.setAttribute("questionCount", questionList.size());
        session.setAttribute(TEST_ID, testId);
        session.setAttribute(QUESTION_ID, questionId);
        req.setAttribute("question", questionList.get(questionId));
        session.setAttribute("answers", answerList);
        session.setAttribute("questions", questionList);
        session.setAttribute("time", time);

        page = Path.QUESTIONS_PAGE;
        return page;
    }
}
