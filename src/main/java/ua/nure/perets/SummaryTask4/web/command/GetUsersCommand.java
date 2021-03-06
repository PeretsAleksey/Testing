package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.dao.impl.UserDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetUsersCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetUsersCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        HttpSession session = req.getSession(false);

        String page = Path.ERROR_PAGE;

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        UserDaoImpl userDao = new UserDaoImpl();

        if (user.getRoleId() == 0) {
            List<User> usersList = userDao.getAllUser();
            session.setAttribute("usersList", usersList);
            LOG.trace("Set the session attribute: usersList --> " + usersList);
            page = Path.GET_USERS;
        } else if (user.getRoleId() == 1) {
            User userInfo = userDao.getUserByLogin(user.getLogin());
            System.out.println(userInfo);
            session.setAttribute("userInfo", userInfo);
            LOG.trace("Set the session attribute: userInfo --> " + userInfo);
            page = Path.GET_USER;
        }

        LOG.debug("Command starts");

        return page;
    }
}
