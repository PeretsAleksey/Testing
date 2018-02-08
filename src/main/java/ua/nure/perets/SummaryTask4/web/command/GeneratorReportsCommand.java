package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.ReportGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class GeneratorReportsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GeneratorReportsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        String page = Path.REPORTS_PAGE;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        if (user.getRoleId() == 1) {
            throw new AppException("access denied");
        }

        boolean status = new ReportGenerator().createReports();
        if (!status) {
            throw new AppException("Report not generated");
        }

        session.setAttribute("status", status);
        LOG.trace("Set the session attribute: status --> " + status);

        LOG.debug("Command finished");

        return page;
    }
}
