package ua.nure.perets.SummaryTask4.dao;

import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    void addUser(List<User> users);

    void delUser(int id);

    boolean updateUserStatusByLogin(String login) throws DBException;

    void createUser(String login, String hash, String fName, String lName, String eMail) throws DBException;
}
