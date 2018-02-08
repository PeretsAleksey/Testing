package ua.nure.perets.SummaryTask4;

import ua.nure.perets.SummaryTask4.dao.impl.*;
import ua.nure.perets.SummaryTask4.exeption.DBException;
import ua.nure.perets.SummaryTask4.utils.ReportGenerator;

import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException, DBException {

        UserDaoImpl userDao = new UserDaoImpl();
        AnswerDaoImpl answerDao = new AnswerDaoImpl();
        QuestionDaoImpl questionDao = new QuestionDaoImpl();
        TestDaoImpl testDao = new TestDaoImpl();
        ThemeDaoImpl themeDao = new ThemeDaoImpl();
        UserTestDaoImpl userTestDao = new UserTestDaoImpl();
        /*User user = userDao.getUserByLogin("user");
        System.out.println(user);*/

        //userDao.createUser("user2", "123456", "fname", "lname", "mail2@mail.ru");
        // System.out.println(userDao.updateUserStatusByLogin("user2"));
        /*List <User> users = userDao.getAllUser();
        for (User user : users){
            System.out.println(user);
        }*/
//--------------------------------------------------------------------------------
        // themeDao.createTheme("Умножение и деление");
        //  themeDao.updateThemeName("updateThemeName", String.valueOf(1));
        /*List<Theme> themes = themeDao.getThemes();
        for (Theme theme : themes){
            System.out.println(theme);
        }*/
        //themeDao.deleteTheme(String.valueOf(2));
        //themeDao.updateThemeStatusById(1);
        // System.out.println(themeDao.findThemeById(1));
        //----------------------------------------

        //testDao.createTest("тест2", "5", String.valueOf(60), 8);
        //testDao.updateTestDifficulty("15", String.valueOf(1));
        // testDao.updateTestName("test1", String.valueOf(1));
        //testDao.updateTestTime("120", 1);
        //---------------------------------------------
        //questionDao.createQuestion("В каком выражении1111111?", 1);
        //  questionDao.updateQuestionName("выражении222222", String.valueOf(2));
        /*questionDao.deleteQuestion(String.valueOf(1));
        List<Question> questions = questionDao.getQuestions();
        for (Question question : questions) {
            System.out.println(question);
        }*/
        //--------------------------------------------
        //  answerDao.createAnswer("Answer", 2);
        //answerDao.updateAnswerName("new", String.valueOf(6));
        // System.out.println(answerDao.findAnswersByQuestionId(2));
        // answerDao.deleteAnswer(String.valueOf(6));
        // answerDao.updateAnswerStatusById("4");
        //  System.out.println(answerDao.findAnswerById("4"));
        /*List<Answer> list = answerDao.getAnswers();
        for (Answer l : list) {
            System.out.println(l);
        }*/
        //----------------------------------------------------
        //userTestDao.createUsersTests(6, 27, 55);
        //System.out.println(userTestDao.findUsersTestsByUserId(27));
        //System.out.println(userTestDao.getUsersTests());

//-------------------------------------

        new ReportGenerator().createReports();

    }
}
