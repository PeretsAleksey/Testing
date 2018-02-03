package ua.nure.perets.SummaryTask4.web;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.web.command.Command;
import ua.nure.perets.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter("command");
        System.out.println(commandName);

        if (!commandName.equals("login") && req.getSession().getAttribute("user") == null
                && !commandName.equals("RegistrationPage") && !commandName.equals("registration")) {
            req.getRequestDispatcher(Path.LOGIN_PAGE).forward(req, resp);
            req.getSession().invalidate();
            return;
        }

        Command command = CommandContainer.get(commandName);
        System.out.println(command);

        String forward = Path.ERROR_PAGE;

        try {
            forward = command.execute(req, resp);
        } catch (AppException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher(forward).forward(req, resp);
    }
}
