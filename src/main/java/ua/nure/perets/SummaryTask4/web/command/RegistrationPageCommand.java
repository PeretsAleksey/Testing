package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegistrationPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException,
            AppException, SQLException {

        LOG.debug("Command starts");
        LOG.debug("Command finished");

        return Path.REGISTRATION_PAGE;

    }
}
