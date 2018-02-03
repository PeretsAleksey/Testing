package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.ThemeDaoImpl;
import ua.nure.perets.SummaryTask4.dao.impl.UserDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.Password;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand extends Command {

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException, AppException, SQLException {

        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByLogin(login);
        if (user == null || !Password.hash(password).equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        int userId = user.getId();
        String page = Path.ERROR_PAGE;

        if (user.getRoleId() == 0) {
            page = Path.ADMIN_PAGE;


        } else {
            if (user.isBlocked()) {
                throw new AppException("user is blocked");
            }
            page = Path.CLIENT_PAGE;
        }

        session.setAttribute("user", user);
        session.setAttribute("userId", userId);
        session.setAttribute("themesList", new ThemeDaoImpl().getThemes());
        return page;
    }
}

