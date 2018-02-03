package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SettingsCommand extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {
        String page = Path.SETTINGS_PAGE;

        return page;
    }
}
