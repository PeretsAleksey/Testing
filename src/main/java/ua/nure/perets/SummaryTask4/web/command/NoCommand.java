package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class NoCommand extends Command {

    private static final Logger LOG = Logger.getLogger(NoCommand.class);


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, AppException {
        LOG.debug("Command starts");

        String errorMessage = "No such command";
        req.setAttribute("errorMessage", errorMessage);
        LOG.error("Set the request attribute: errorMessage --> " + errorMessage);

        LOG.debug("Command finished");
        return Path.ERROR_PAGE;
    }
}
