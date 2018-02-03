package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class Command {

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException, AppException, SQLException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}

