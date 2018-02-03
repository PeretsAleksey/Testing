package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class HomePageCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;
        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");

        if (user.getRoleId() == 0) {
            page = Path.ADMIN_PAGE;
            return page;
        }
        if (user.getRoleId() == 1) {
            page = Path.CLIENT_PAGE;
            return page;
        }
        return page;
    }
}
