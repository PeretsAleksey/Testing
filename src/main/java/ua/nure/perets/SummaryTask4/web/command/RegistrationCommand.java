package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.UserDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.Password;
import ua.nure.perets.SummaryTask4.utils.Validators;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.perets.SummaryTask4.utils.EmailSender.sender;

public class RegistrationCommand extends Command {

    private static final String PASSWORD = "Password";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException,
            AppException, SQLException {

        String page = Path.ERROR_PAGE;

        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users = userDao.getAllUser();

        List<String> logins = new ArrayList<>(users.size());
        for (User user : users) {
            logins.add(user.getLogin());
        }

        String login = req.getParameter("setLogin");

        if (Validators.validateOnlyLatinFields(login, "Login", 20) != null) {
            throw new AppException(Validators.validateOnlyLatinFields(login, "Login", 20));
        }

        if (logins.contains(login)) {
            throw new AppException("Login is already exists");
        }

        String password = req.getParameter("setPassword");

        if (Validators.validateOnlyLatinFields(password, PASSWORD, 20) != null) {
            throw new AppException(Validators.validateOnlyLatinFields(password, PASSWORD, 20));
        }

        String confirmPassword = req.getParameter("confirmPassword");

        Validators.validateFields(confirmPassword, "Confirm password", 20);

        if (Validators.validateOnlyLatinFields(confirmPassword, "Confirm password", 20) != null) {
            throw new AppException(Validators.validateOnlyLatinFields(confirmPassword, "Confirm password", 20));
        }

        if (!password.equals(confirmPassword)) {
            throw new AppException("Password mismatch");
        }

        String fName = req.getParameter("setFName");

        if (Validators.validateFields(fName, PASSWORD, 20) != null) {
            throw new AppException(Validators.validateFields(fName, "First Name", 20));
        }

        String lName = req.getParameter("setLName");

        if (Validators.validateFields(lName, PASSWORD, 20) != null) {
            throw new AppException(Validators.validateFields(lName, "Last Name", 20));
        }

        String eMail = req.getParameter("setEMail");

        if (Validators.validateEMail(eMail, 30) != null) {
            throw new AppException(Validators.validateEMail(eMail, 30));
        }

        System.out.println(Password.hash(password).length());
        userDao.createUser(login, Password.hash(password), fName, lName, eMail);

        page = Path.ACTION_PAGE;
        req.setAttribute("register", true);
        sender(eMail, fName);
        return page;
    }
}
