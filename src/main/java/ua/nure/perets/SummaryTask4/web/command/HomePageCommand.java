package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
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

    private static final Logger LOG = Logger.getLogger(HomePageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        if (user.getRoleId() == 0) {
            page = Path.ADMIN_PAGE;
            return page;
        }
        if (user.getRoleId() == 1) {
            page = Path.CLIENT_PAGE;
            return page;
        }

        LOG.debug("Command finished");

        return page;
    }
}
