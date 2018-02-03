package ua.nure.perets.SummaryTask4.dao;

import ua.nure.perets.SummaryTask4.bean.Test;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.util.List;

public interface TestDao {

    void updateTestName(String name, String id) throws DBException;

    void updateTestDifficulty(String difficulty, String id) throws DBException;

    void updateTestTime(String time, String id) throws DBException;

    void createTest(String name, String difficulty, String time, int themeId) throws DBException;

    List<Test> findTestsByTheme(int themeId) throws DBException;

    void deleteTest(String testId) throws DBException;

    int findTimeForTestByTestId(int testId) throws DBException;

    List<Test> getTests() throws DBException;
}