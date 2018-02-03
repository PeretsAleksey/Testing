package ua.nure.perets.SummaryTask4.dao;

import ua.nure.perets.SummaryTask4.bean.UserTest;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.util.List;

public interface UserTestDao {

    List<UserTest> getUsersTests() throws DBException;

    List<UserTest> findUsersTestsByUserId(int userId) throws DBException;

    void createUsersTests(int testId, int userId, double result) throws DBException;

}
