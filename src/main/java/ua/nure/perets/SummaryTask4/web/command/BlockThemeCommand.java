package ua.nure.perets.SummaryTask4.web.command;

import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.dao.impl.ThemeDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class BlockThemeCommand  extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        String page = Path.ERROR_PAGE;

        HttpSession session = req.getSession(false);

        ThemeDaoImpl themeDao = new ThemeDaoImpl();

        int themeId = Integer.parseInt(req.getParameter("themeId"));

        boolean status = themeDao.updateThemeStatusById(themeId);

        req.setAttribute("status", status);
        session.setAttribute("themesList", themeDao.getThemes());

        page = Path.ADMIN_PAGE;

        return page;
    }
}
