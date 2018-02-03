package ua.nure.perets.SummaryTask4.web.command;

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

public class BlockUserCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        UserDaoImpl userDao = new UserDaoImpl();

        String login = req.getParameter("login");

        boolean status = userDao.updateUserStatusByLogin(login);

        req.setAttribute("login", login);

        req.setAttribute("status", status);

        List<User> list = userDao.getAllUser();

        session.setAttribute("usersList", list);

        page = Path.GET_USERS;

        return page;

    }
}
