package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.Theme;
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
import java.util.List;

public class LoginCommand extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        String password = req.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);
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

        List<Theme> themeList = new ThemeDaoImpl().getThemes();

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);
        session.setAttribute("userId", userId);
        LOG.trace("Set the session attribute: userId --> " + userId);
        session.setAttribute("successLogin", true);
        LOG.trace("Set the session attribute: successLogin --> " + true);
        session.setAttribute("themesList", themeList);
        LOG.trace("Set the session attribute: themesList --> " + themeList);

        LOG.debug("Command finished");

        return page;
    }
}

