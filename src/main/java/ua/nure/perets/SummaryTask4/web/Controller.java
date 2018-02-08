package ua.nure.perets.SummaryTask4.web;

import org.apache.log4j.Logger;
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

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("DoGet");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.trace("DoPost");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = Path.ERROR_PAGE;

        String commandName = req.getParameter("command");
        System.out.println(commandName);

        Command command = CommandContainer.get(commandName);
        System.out.println(command);

        LOG.debug("command: " + forward);

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
